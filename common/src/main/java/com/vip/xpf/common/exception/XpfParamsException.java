package com.vip.xpf.common.exception;

public class XpfParamsException extends XpfRunTimeException {

    private static final int EXCEPTION_CODE = 400;

    public XpfParamsException(String message) {
        super(EXCEPTION_CODE, message);
    }

}
