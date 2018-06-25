package ${package};

<#list imports as import>
import ${import};
</#list>
import org.springframework.stereotype.Repository;

/**
 <#if (tableInfo.comment)??>
 * @desc：${tableInfo.comment}Dao
 <#else >
 *
 </#if>
 * @author ${author}
 * @date ${date}
 */
@Repository
public class ${tableInfo.className}DaoImpl extends BaseDaoImpl<${tableInfo.className}Mapper, ${tableInfo.className}> implements ${tableInfo.className}Dao {

}