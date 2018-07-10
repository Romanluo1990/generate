package com.vip.xpf.logic.controller;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.api.common.util.PageUtils;
import com.vip.xpf.api.controller.BaseController;
import com.vip.xpf.common.util.bean.BeanUtils;
import com.vip.xpf.dao.common.sql.PageSelect;
import com.vip.xpf.dao.common.sql.SelectCondition;
import com.vip.xpf.logic.controller.form.SalePlanProductForm;
import com.vip.xpf.logic.controller.vo.SalePlanProductVo;
import com.vip.xpf.logic.controller.vo.SalePlanVo;
import com.vip.xpf.model.SalePlanProduct;
import com.vip.xpf.search.searcher.SalePlanSearcher;
import com.vip.xpf.service.SalePlanProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @desc：选品表Controller
 * @author romanluo
 * @date 2018/07/09
 */
@Api("选品表接口")
@RestController
@RequestMapping("salePlanProduct")
public class SalePlanProductController extends BaseController<SalePlanProductService> {

	@Resource
	private SalePlanSearcher salePlanSearcher;

	@PostMapping
	@ApiOperation(value = "增加或修改选品表", notes = "有id更新，无id新增")
	public void saveOrUpdate(@RequestBody SalePlanProductForm SalePlanProductForm) {
		service.saveOrUpdate(BeanUtils.map(SalePlanProductForm, SalePlanProduct.class));
	}

	@DeleteMapping("id_{id}")
	@ApiOperation("根据id删除选品表")
	public void delete(@ApiParam("用户id") long id) {
		service.deleteById(id);
	}

	@GetMapping("id_{id}")
	@ApiOperation("根据id获取选品表")
	public SalePlanProductVo get(@ApiParam("选品表id") @PathVariable long id) {
		return BeanUtils.map(service.getById(id), SalePlanProductVo.class);
	}

	@GetMapping("page")
	@ApiOperation("分页查询")
	public PageInfo<SalePlanVo> page(@ModelAttribute PageSelect pageSelect, List<SelectCondition> conditions) {
		return PageUtils.convert(salePlanSearcher.search(pageSelect, conditions), SalePlanVo.class);
	}
}
