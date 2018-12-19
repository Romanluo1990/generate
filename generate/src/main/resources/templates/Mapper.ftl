package ${package};

import tk.mybatis.mapper.common.Mapper;
<#list imports as import>
import ${import};
</#list>

/**
 <#if (tableInfo.comment)??>
 * @desc：${tableInfo.comment}Repository
 <#else >
 *
 </#if>
 * @author ${author}
 * @date ${date}
 */
public interface ${tableInfo.className}Repository extends Mapper<${tableInfo.className}> {

}