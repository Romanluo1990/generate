package com.vip.xpf.logic.controller;;

import com.vip.xpf.logic.controller.form.ProductSpuForm;
import com.vip.xpf.logic.controller.vo.ProductSpuVo;
import com.vip.xpf.model.ProductSpu;
import com.vip.xpf.service.ProductSpuService;

import com.vip.xpf.api.controller.BaseController;
import com.vip.xpf.common.util.bean.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


/**
 * @desc：商品表Controller
 * @author romanluo
 * @date 2018/07/09
 */
@Api("商品表接口")
@RestController
@RequestMapping("productSpu")
public class ProductSpuController extends BaseController<ProductSpuService> {

    @PostMapping
    @ApiOperation(value = "增加或修改商品表", notes = "有id更新，无id新增")
    public void saveOrUpdate(@RequestBody ProductSpuForm ProductSpuForm) {
        service.saveOrUpdate(BeanUtils.map(ProductSpuForm, ProductSpu.class));
    }

    @DeleteMapping("id_{id}")
    @ApiOperation("根据id删除商品表")
    public void delete(@ApiParam("用户id") long id) {
        service.deleteById(id);
    }

    @GetMapping("id_{id}")
    @ApiOperation("根据id获取商品表")
    public ProductSpuVo get(@ApiParam("商品表id") @PathVariable long id) {
        return BeanUtils.map(service.getById(id), ProductSpuVo.class);
    }
    
}
