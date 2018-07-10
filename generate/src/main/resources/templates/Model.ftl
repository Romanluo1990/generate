package ${package};

<#list imports as import>
import ${import};
</#list>

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 <#if (tableInfo.comment)??>
 * @desc：${tableInfo.comment}模型
 <#else >
 *
 </#if>
 * @author ${author}
 * @date ${date}
 */
@Data
@Table(name="${tableInfo.tableName}")
public class ${tableInfo.className} implements Identity {

    <#list columnInfos as columnInfo>
    /**
    *${columnInfo.comment!}
    */
    <#if columnInfo.primaryKey>
    @Id
    @GeneratedValue(generator = "JDBC")
    <#else>
    @Column(name = "`${columnInfo.columnName}`")
    </#if>
    private ${columnInfo.propertyType} ${columnInfo.propertyName?uncap_first};

    </#list>
}