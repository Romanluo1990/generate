package com.vip.xpf.controller;

import com.vip.xpf.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<S extends BaseService> {

    @Autowired
    protected S service;
}
