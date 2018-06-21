package com.vip.xpf.controller;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.controller.form.AccountForm;
import com.vip.xpf.controller.vo.AccountVo;
import com.vip.xpf.dao.common.PageSelect;
import com.vip.xpf.dao.common.SelectCondition;
import com.vip.xpf.service.AccountService;
import com.vip.xpf.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description= "用户接口")
@RestController
@RequestMapping("account")
public class AccountController extends BaseController<AccountService>{

    @PostMapping
    @ApiOperation(value = "增加或修改用户信息",notes = "有id更新，无id新增")
    public void saveOrUpdate(@RequestBody AccountForm accountForm){
        service.saveOrUpdate(accountForm.getAccount());
    }

    @DeleteMapping("id_{id}")
    @ApiOperation("根据id删除用户信息")
    public void delete(@ApiParam("用户id") long id){
        service.deleteById(id);
    }

    @GetMapping("id_{id}")
    @ApiOperation("根据id获取用户信息")
    public AccountVo get(@ApiParam("用户id") @PathVariable long id){
        return new AccountVo(service.getById(id));
    }

    @GetMapping("page")
    @ApiOperation("分页获取用户信息")
    public PageInfo<AccountVo> page(@ModelAttribute PageSelect pageSelect){
        return PageUtils.convert(service.page(pageSelect),AccountVo::new);
    }

    @GetMapping("pageByConditions")
    @ApiOperation("按条件分页获取用户信息")
    @ApiImplicitParam(name="conditions",value = "查询条件,exapmle:[{\"fieldName\": \"id\",\"operator\": \"EQ\",\"value\":\"1\"}]",dataType = "String",paramType = "query")
    public PageInfo<AccountVo> pageByConditions(@ModelAttribute PageSelect pageSelect,
                                                 List<SelectCondition> conditions){
        return PageUtils.convert(service.pageByConditions(pageSelect,conditions),AccountVo::new);
    }
}
