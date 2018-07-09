package com.vip.xpf.logic.controller;;

import com.vip.xpf.logic.controller.form.ProductSkuForm;
import com.vip.xpf.logic.controller.vo.ProductSkuVo;
import com.vip.xpf.model.ProductSku;
import com.vip.xpf.service.ProductSkuService;

import com.vip.xpf.api.controller.BaseController;
import com.vip.xpf.common.util.bean.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


/**
 * @desc：商品skuController
 * @author romanluo
 * @date 2018/07/09
 */
@Api("商品sku接口")
@RestController
@RequestMapping("productSku")
public class ProductSkuController extends BaseController<ProductSkuService> {

    @PostMapping
    @ApiOperation(value = "增加或修改商品sku", notes = "有id更新，无id新增")
    public void saveOrUpdate(@RequestBody ProductSkuForm ProductSkuForm) {
        service.saveOrUpdate(BeanUtils.map(ProductSkuForm, ProductSku.class));
    }

    @DeleteMapping("id_{id}")
    @ApiOperation("根据id删除商品sku")
    public void delete(@ApiParam("用户id") long id) {
        service.deleteById(id);
    }

    @GetMapping("id_{id}")
    @ApiOperation("根据id获取商品sku")
    public ProductSkuVo get(@ApiParam("商品skuid") @PathVariable long id) {
        return BeanUtils.map(service.getById(id), ProductSkuVo.class);
    }
    
}
