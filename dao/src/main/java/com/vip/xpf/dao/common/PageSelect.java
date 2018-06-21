package com.vip.xpf.dao.common;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class PageSelect {

    private int pageNo = 1;

    private int pageSize = 10;

    private List<OrderBy> orderByList;

    private Boolean count;

    public String getOrderByString() {
        if (Objects.nonNull(orderByList)) {
            return orderByList.stream()
                    .filter(orderBy -> Objects.nonNull(orderBy.getName()))
                    .map(orderBy -> String.format("%s %s", orderBy.getName(), orderBy.getType()?"asc":"desc"))
                    .collect(Collectors.joining(", "));
        } else {
            return null;
        }
    }

}
