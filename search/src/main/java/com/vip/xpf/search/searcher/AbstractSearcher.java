package com.vip.xpf.search.searcher;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Streams;
import com.vip.xpf.dao.BaseDao;
import com.vip.xpf.dao.common.sql.PageSelect;
import com.vip.xpf.dao.common.sql.SelectCondition;
import com.vip.xpf.dao.common.sql.Symbol;
import com.vip.xpf.model.Identity;
import com.vip.xpf.search.indexmodel.IndexModel;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description:
 */
public abstract class AbstractSearcher<E extends ElasticsearchRepository<I, Long>, I extends IndexModel, D extends BaseDao<S>, S extends Identity> {

	@Autowired
	private E esRepository;

	@Autowired
	protected D dao;

	private Class sourceClassCache;

	public PageInfo<S> search(QueryBuilder query, Pageable pageable) {
		return toPageInfo(esRepository.search(query, pageable));
	}

	public void save(S sourceData) {
		esRepository.save(toIndexData(sourceData));
	}

	public void saveAll(Iterable<S> sourceDatas) {
		List<I> indexDatas = Streams.stream(sourceDatas).parallel().map(this::toIndexData).filter(Objects::nonNull)
				.collect(Collectors.toList());
		esRepository.saveAll(indexDatas);
	}

	public void deleteById(Long id) {
		esRepository.deleteById(id);
	}

	public void deleteAll() {
		esRepository.deleteAll();
	}

	public PageInfo<S> search(PageSelect pageSelect, List<SelectCondition> conditions) {
		Sort sort = getSearchSort(pageSelect);
		Pageable pageable = PageRequest.of(pageSelect.getPageNo() - 1, pageSelect.getPageSize(), sort);
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		conditions.forEach(buildQueryBuilder(queryBuilder));
		return search(queryBuilder, pageable);
	}

	public void importAll() {
		long id = -1;
		int size = 1000;
		while (true) {
			List<S> sourceDatas = listSourceData(id, size);
			if (sourceDatas.isEmpty()) {
				break;
			}
			saveAll(sourceDatas);
			id = sourceDatas.get(sourceDatas.size() - 1).getId();
		}
	}

	/**
	 * 源数据转索引数据
	 * @param sourceData
	 * @return
	 */
	protected abstract I toIndexData(S sourceData);

	private Sort getSearchSort(PageSelect pageSelect) {
		Sort sort;
		if (pageSelect.getOrderByList() == null || pageSelect.getOrderByList().isEmpty()) {
			sort = Sort.unsorted();
		} else {
			sort = Sort.by(pageSelect.getOrderByList().stream()
					.map(orderBy -> new Sort.Order(orderBy.getType() ? Sort.Direction.ASC : Sort.Direction.DESC,
							orderBy.getName())).collect(Collectors.toList()));
		}
		return sort;
	}

	private Consumer<SelectCondition> buildQueryBuilder(BoolQueryBuilder queryBuilder) {
		return selectCondition -> {
			String operator = selectCondition.getOperator();
			String fieldName = selectCondition.getFieldName();
			String value = selectCondition.getValue();
			if (Symbol.EQ.getOperator().equals(operator)) {
				queryBuilder.must(QueryBuilders.termQuery(fieldName, value));
			} else if (Symbol.LE.getOperator().equals(operator)) {
				queryBuilder.must(QueryBuilders.rangeQuery(fieldName).lte(value));
			} else if (Symbol.LT.getOperator().equals(operator)) {
				queryBuilder.must(QueryBuilders.rangeQuery(fieldName).lt(value));
			} else if (Symbol.GT.getOperator().equals(operator)) {
				queryBuilder.must(QueryBuilders.rangeQuery(fieldName).gt(value));
			} else if (Symbol.GE.getOperator().equals(operator)) {
				queryBuilder.must(QueryBuilders.rangeQuery(fieldName).gte(value));
			} else if (Symbol.NE.getOperator().equals(operator)) {
				queryBuilder.mustNot(QueryBuilders.termQuery(fieldName, value));
			} else if (Symbol.LIKE.getOperator().equals(operator)) {
				queryBuilder.must(QueryBuilders.matchQuery(fieldName, value));
			}
		};
	}

	private PageInfo<S> toPageInfo(Page<I> page) {
		PageInfo<S> dest = new PageInfo<>();
		Pageable pageable = page.getPageable();
		dest.setPageNum(page.getNumber() + 1);
		dest.setPageSize(pageable.getPageSize());
		dest.setSize(page.getNumberOfElements());
		dest.setOrderBy(pageable.getSort().toString());
		dest.setStartRow((int) pageable.getOffset() + 1);
		dest.setEndRow(dest.getStartRow() - 1 + dest.getSize());
		dest.setTotal(page.getTotalElements());
		dest.setPages(page.getTotalPages());
		dest.setFirstPage(1);
		dest.setPrePage(dest.getPageNum() - 1);
		dest.setNextPage(dest.getPageNum() + 1);
		dest.setLastPage(dest.getPages());
		dest.setHasPreviousPage(dest.getPrePage() > 0);
		dest.setHasNextPage(dest.getNextPage() < dest.getLastPage());
		dest.setList(toSourceData(page.getContent()));
		return dest;
	}

	private List<S> toSourceData(List<I> indexDatas) {
		List<Long> ids = indexDatas.stream().collect(Collectors.mapping(IndexModel::getId, Collectors.toList()));
		Map<Long, S> salePlanMap = dao.listByIds(ids).parallelStream()
				.collect(Collectors.toMap(Identity::getId, Function.identity()));
		return indexDatas.stream().map(IndexModel::getId).map(salePlanMap::get).collect(Collectors.toList());
	}

	private List<S> listSourceData(long id, int size) {
		return dao.listById(id, size);
	}

	private Class<S> getEntityClass() {
		if (sourceClassCache == null) {
			ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
			sourceClassCache = (Class) parameterizedType.getActualTypeArguments()[3];
		}
		return sourceClassCache;
	}
}
