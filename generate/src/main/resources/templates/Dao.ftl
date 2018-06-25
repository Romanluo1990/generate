package ${package};

<#list imports as import>
import ${import};
</#list>

/**
 <#if (tableInfo.comment)??>
 * @desc：${tableInfo.comment}Dao
 <#else >
 *
 </#if>
 * @author ${author}
 * @date ${date}
 */
public interface ${tableInfo.className}Dao extends BaseDao<${tableInfo.className}> {


}