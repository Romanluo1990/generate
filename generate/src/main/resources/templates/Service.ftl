package ${package};

<#list imports as import>
import ${import};
</#list>
import org.springframework.stereotype.Service;

/**
 <#if (tableInfo.comment)??>
 * @desc：${tableInfo.comment}Service
 <#else >
 *
 </#if>
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${tableInfo.className}Service extends BaseService<${tableInfo.className}Dao> {


}