package ${package};;

<#list imports as import>
import ${import};
</#list>

import com.vip.xpf.api.controller.BaseController;
import com.vip.xpf.common.util.bean.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


/**
<#if (tableInfo.comment)??>
 * @desc：${tableInfo.comment}Controller
<#else >
 *
</#if>
 * @author ${author}
 * @date ${date}
 */
@Api("${tableInfo.comment}接口")
@RestController
@RequestMapping("${tableInfo.className?uncap_first}")
public class ${tableInfo.className}Controller extends BaseController<${tableInfo.className}Service> {

    @PostMapping
    @ApiOperation(value = "增加或修改${tableInfo.comment}", notes = "有id更新，无id新增")
    public void saveOrUpdate(@RequestBody ${tableInfo.className}Form ${tableInfo.className}Form) {
        service.saveOrUpdate(BeanUtils.map(${tableInfo.className}Form, ${tableInfo.className}.class));
    }

    @DeleteMapping("id_{id}")
    @ApiOperation("根据id删除${tableInfo.comment}")
    public void delete(@ApiParam("用户id") long id) {
        service.deleteById(id);
    }

    @GetMapping("id_{id}")
    @ApiOperation("根据id获取${tableInfo.comment}")
    public ${tableInfo.className}Vo get(@ApiParam("${tableInfo.comment}id") @PathVariable long id) {
        return BeanUtils.map(service.getById(id), ${tableInfo.className}Vo.class);
    }
    
}
