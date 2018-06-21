package com.vip.xpf.exception;

public class XpfRunTimeException extends RuntimeException {

    private static final int DEFAULT_ERR_CODE = 500;

    private final int errCode;

    public XpfRunTimeException() {
        this(DEFAULT_ERR_CODE);
    }

    public XpfRunTimeException(int errCode) {
        this(errCode, (String) null);
    }

    public XpfRunTimeException(String message) {
        this(DEFAULT_ERR_CODE, message);
    }

    public XpfRunTimeException(int errCode, String message) {
        this(errCode, message, null);
    }

    public XpfRunTimeException(Throwable cause) {
        this(DEFAULT_ERR_CODE, cause);
    }

    public XpfRunTimeException(int errCode, Throwable cause) {
        this(errCode, (cause == null ? null : cause.toString()), cause);
    }

    public XpfRunTimeException(String message, Throwable cause) {
        this(DEFAULT_ERR_CODE, message, cause);
    }

    public XpfRunTimeException(int errCode, String message, Throwable cause) {
        this(errCode, message, cause, true, true);
    }

    public XpfRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        this(DEFAULT_ERR_CODE, message, cause, enableSuppression, writableStackTrace);
    }

    public XpfRunTimeException(int errCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
    }

    public int getExceptionCode() {
        return errCode;
    }
}
