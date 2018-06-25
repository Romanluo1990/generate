package ${package};

import com.vip.xpf.dao.common.ConditionsQueryMapper;
<#list imports as import>
import ${import};
</#list>

/**
 <#if (tableInfo.comment)??>
 * @desc：${tableInfo.comment}Mapper
 <#else >
 *
 </#if>
 * @author ${author}
 * @date ${date}
 */
public interface ${tableInfo.className}Mapper extends ConditionsQueryMapper<${tableInfo.className}> {


}