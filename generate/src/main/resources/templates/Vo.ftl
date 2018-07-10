package ${package};

<#list imports as import>
import ${import};
</#list>

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 <#if (tableInfo.comment)??>
 * @desc：${tableInfo.comment}VO模型
 <#else >
 *
 </#if>
 * @author ${author}
 * @date ${date}
 */
@Data
public class ${tableInfo.className}Vo{

    <#list columnInfos as columnInfo>
    @ApiModelProperty(name = "${columnInfo.comment!}", example = "${columnInfo.defaultValue!}")
    private ${columnInfo.propertyType} ${columnInfo.propertyName?uncap_first};

    </#list>
}