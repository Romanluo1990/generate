package com.vip.xpf.dao.common;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

public class ConditionsSelectProvider extends MapperTemplate {

    public ConditionsSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String listByConditions(MappedStatement ms){
        Class<?> entityClass = getEntityClass(ms);
        String tableName = tableName(entityClass);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        String sql = "SELECT * FROM %s <where> <if test=\"!conditions.isEmpty()\">" +
                " <foreach item=\"condition\" index=\"index\" collection=\"conditions\" separator=\"AND\" >" +
                " `${condition.fieldName}` ${condition.operator} #{condition.value} </foreach> </if></where>";
        return String.format(sql, tableName);
    }

    public String countByConditions(MappedStatement ms){
        Class<?> entityClass = getEntityClass(ms);
        String tableName = tableName(entityClass);

        String sql = "SELECT count(1) FROM %s <where> <if test=\"!conditions.isEmpty()\">" +
                " <foreach item=\"condition\" index=\"index\" collection=\"conditions\" separator=\"AND\" >" +
                " `${condition.fieldName}` ${condition.operator} #{condition.value} </foreach> </if></where>";
        return String.format(sql, tableName);
    }
}
