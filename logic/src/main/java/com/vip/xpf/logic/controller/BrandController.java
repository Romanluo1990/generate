package com.vip.xpf.logic.controller;;

import com.vip.xpf.logic.controller.form.BrandForm;
import com.vip.xpf.logic.controller.vo.BrandVo;
import com.vip.xpf.model.Brand;
import com.vip.xpf.service.BrandService;

import com.vip.xpf.api.controller.BaseController;
import com.vip.xpf.common.util.bean.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


/**
 * @desc：品牌表Controller
 * @author romanluo
 * @date 2018/07/09
 */
@Api("品牌表接口")
@RestController
@RequestMapping("brand")
public class BrandController extends BaseController<BrandService> {

    @PostMapping
    @ApiOperation(value = "增加或修改品牌表", notes = "有id更新，无id新增")
    public void saveOrUpdate(@RequestBody BrandForm BrandForm) {
        service.saveOrUpdate(BeanUtils.map(BrandForm, Brand.class));
    }

    @DeleteMapping("id_{id}")
    @ApiOperation("根据id删除品牌表")
    public void delete(@ApiParam("用户id") long id) {
        service.deleteById(id);
    }

    @GetMapping("id_{id}")
    @ApiOperation("根据id获取品牌表")
    public BrandVo get(@ApiParam("品牌表id") @PathVariable long id) {
        return BeanUtils.map(service.getById(id), BrandVo.class);
    }
    
}
