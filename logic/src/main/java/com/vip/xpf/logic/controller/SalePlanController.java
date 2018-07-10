package com.vip.xpf.logic.controller;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.api.common.util.PageUtils;
import com.vip.xpf.api.controller.BaseController;
import com.vip.xpf.common.util.bean.BeanUtils;
import com.vip.xpf.dao.common.sql.PageSelect;
import com.vip.xpf.dao.common.sql.SelectCondition;
import com.vip.xpf.logic.controller.form.SalePlanForm;
import com.vip.xpf.logic.controller.vo.SalePlanVo;
import com.vip.xpf.model.SalePlan;
import com.vip.xpf.search.searcher.SalePlanSearcher;
import com.vip.xpf.service.SalePlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @desc：档期表Controller
 * @author romanluo
 * @date 2018/07/09
 */
@Api("档期表接口")
@RestController
@RequestMapping("salePlan")
public class SalePlanController extends BaseController<SalePlanService> {

	@Resource
	private SalePlanSearcher salePlanSearcher;

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

	@GetMapping("page")
	@ApiOperation("分页查询")
	@ApiImplicitParam(name = "conditions", value = "查询条件,exapmle:[{\"fieldName\": \"id\",\"operator\": \"EQ\",\"value\":\"1\"}]", dataType = "String", paramType = "query")
	public PageInfo<SalePlanVo> page(@ModelAttribute PageSelect pageSelect, List<SelectCondition> conditions) {
		return PageUtils.convert(salePlanSearcher.search(pageSelect, conditions), SalePlanVo.class);
	}

}
