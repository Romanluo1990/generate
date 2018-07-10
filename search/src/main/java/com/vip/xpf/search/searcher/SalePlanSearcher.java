package com.vip.xpf.search.searcher;

import com.vip.xpf.dao.*;
import com.vip.xpf.dao.impl.event.ModifySalePlanEvent;
import com.vip.xpf.model.*;
import com.vip.xpf.search.indexmodel.SalePlanIndex;
import com.vip.xpf.search.repository.SalePlanRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/9
 * @Description:
 */
@Repository
public class SalePlanSearcher extends AbstractSearcher<SalePlanRepository, SalePlanIndex, SalePlanDao, SalePlan>
		implements ApplicationListener<ModifySalePlanEvent> {

	/**
	 * 1.cache为空,直接更新,完成时检测cache为true,重新更新索引
	 * 2.为false时，更新为true
	 * 3.为true，不做处理
	 */
	private final ConcurrentHashMap<Long, Boolean> updateFlagCache = new ConcurrentHashMap<>();

	@Resource
	private SalePlanProductDao salePlanProductDao;

	@Resource
	private ProductSpuDao productSpuDao;

	@Resource
	private CategoryDao categoryDao;

	@Resource
	private BrandDao brandDao;

	@Override
	protected SalePlanIndex toIndexData(SalePlan salePlan) {
		SalePlanIndex salePlanIndex = new SalePlanIndex();
		salePlanIndex.setId(salePlan.getId());
		salePlanIndex.setName(salePlan.getName());
		List<SalePlanProduct> salePlanProducts = salePlanProductDao.listByPlanId(salePlan.getId());
		if (!salePlanProducts.isEmpty()) {
			List<Long> productIds = salePlanProducts.stream().map(SalePlanProduct::getProductId)
					.collect(Collectors.toList());
			List<ProductSpu> products = productIds.stream().map(productSpuDao::getById).filter(Objects::nonNull)
					.collect(Collectors.toList());
			salePlanIndex.setProduct(products.stream().map(ProductSpu::getTitle).collect(Collectors.joining(" ")));

			List<Category> categories = categoryDao.listByIds(
					products.stream().map(ProductSpu::getCategoryId).distinct().collect(Collectors.toList()));
			salePlanIndex.setCategory(categories.stream().map(Category::getCateName).collect(Collectors.joining(" ")));

			List<Brand> brands = brandDao
					.listByIds(products.stream().map(ProductSpu::getBrandId).distinct().collect(Collectors.toList()));
			salePlanIndex.setBrand(brands.stream().map(Brand::getBrandCnName).collect(Collectors.joining(" ")));
		}
		return salePlanIndex;
	}

	private void save(SalePlanProduct salePlanProduct) {
		long planId = salePlanProduct.getPlanId();
		Boolean cacheFlag = updateFlagCache.get(planId);
		if (cacheFlag == null) {
			updateFlagCache.putIfAbsent(planId, false);
			//保存更新索引
			save(dao.getById(planId));
			if (updateFlagCache.get(planId)) {
				save(salePlanProduct);
			}
			updateFlagCache.remove(planId);
		} else if (cacheFlag == false) {
			updateFlagCache.replace(planId, true);
		}
	}

	@Override
	public void onApplicationEvent(ModifySalePlanEvent event) {
		Identity entity = event.getEntity();
		if (entity instanceof SalePlan) {
			save((SalePlan) entity);
		} else if (entity instanceof SalePlanProduct) {
			save((SalePlanProduct) entity);
		}
	}
}
