package com.vip.xpf.logic.controller;;

import com.vip.xpf.logic.controller.form.SalePlanForm;
import com.vip.xpf.logic.controller.vo.SalePlanVo;
import com.vip.xpf.model.SalePlan;
import com.vip.xpf.service.SalePlanService;

import com.vip.xpf.api.controller.BaseController;
import com.vip.xpf.common.util.bean.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


/**
 * @desc：档期表Controller
 * @author romanluo
 * @date 2018/07/09
 */
@Api("档期表接口")
@RestController
@RequestMapping("salePlan")
public class SalePlanController extends BaseController<SalePlanService> {

    @PostMapping
    @ApiOperation(value = "增加或修改档期表", notes = "有id更新，无id新增")
    public void saveOrUpdate(@RequestBody SalePlanForm SalePlanForm) {
        service.saveOrUpdate(BeanUtils.map(SalePlanForm, SalePlan.class));
    }

    @DeleteMapping("id_{id}")
    @ApiOperation("根据id删除档期表")
    public void delete(@ApiParam("用户id") long id) {
        service.deleteById(id);
    }

    @GetMapping("id_{id}")
    @ApiOperation("根据id获取档期表")
    public SalePlanVo get(@ApiParam("档期表id") @PathVariable long id) {
        return BeanUtils.map(service.getById(id), SalePlanVo.class);
    }
    
}
