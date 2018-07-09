package com.vip.xpf.logic.controller;;

import com.vip.xpf.logic.controller.form.CategoryForm;
import com.vip.xpf.logic.controller.vo.CategoryVo;
import com.vip.xpf.model.Category;
import com.vip.xpf.service.CategoryService;

import com.vip.xpf.api.controller.BaseController;
import com.vip.xpf.common.util.bean.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


/**
 * @desc：品类表Controller
 * @author romanluo
 * @date 2018/07/09
 */
@Api("品类表接口")
@RestController
@RequestMapping("category")
public class CategoryController extends BaseController<CategoryService> {

    @PostMapping
    @ApiOperation(value = "增加或修改品类表", notes = "有id更新，无id新增")
    public void saveOrUpdate(@RequestBody CategoryForm CategoryForm) {
        service.saveOrUpdate(BeanUtils.map(CategoryForm, Category.class));
    }

    @DeleteMapping("id_{id}")
    @ApiOperation("根据id删除品类表")
    public void delete(@ApiParam("用户id") long id) {
        service.deleteById(id);
    }

    @GetMapping("id_{id}")
    @ApiOperation("根据id获取品类表")
    public CategoryVo get(@ApiParam("品类表id") @PathVariable long id) {
        return BeanUtils.map(service.getById(id), CategoryVo.class);
    }
    
}
