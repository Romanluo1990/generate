package com.vip.xpf.dao.common;

public class OrderBy {

    private String name;

    private boolean type; //true  asc ; false desc

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = "desc".equals(type.toLowerCase()) ? false : true;
    }

    public String getTypeString(){
        return type?"asc":"desc";
    }
}