package com.vip.xpf.dao.common;

public enum  Symbol {
    EQ("="), NE("!="), LT("<"), GT(">"), LE("<="), GE(">="), LIKE("LIKE"), FROM(">="), TO("<=");
    private String operator;

    Symbol(String operator)
    {
        this.operator = operator;
    }

    public String getOperator()
    {
        return operator;
    }

}
