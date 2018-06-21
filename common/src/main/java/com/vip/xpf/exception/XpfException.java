package com.vip.xpf.exception;

public class XpfException extends Exception {

    private static final int DEFAULT_ERR_CODE = 500;

    private final int errCode;

    public XpfException() {
        this(DEFAULT_ERR_CODE);
    }

    public XpfException(int errCode) {
        this(errCode, (String) null);
    }

    public XpfException(String message) {
        this(DEFAULT_ERR_CODE, message);
    }

    public XpfException(int errCode, String message) {
        this(errCode, message, null);
    }

    public XpfException(Throwable cause) {
        this(DEFAULT_ERR_CODE, cause);
    }

    public XpfException(int errCode, Throwable cause) {
        this(errCode, (cause == null ? null : cause.toString()), cause);
    }

    public XpfException(String message, Throwable cause) {
        this(DEFAULT_ERR_CODE, message, cause);
    }

    public XpfException(int errCode, String message, Throwable cause) {
        this(errCode, message, cause, true, true);
    }

    public XpfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        this(DEFAULT_ERR_CODE, message, cause, enableSuppression, writableStackTrace);
    }

    public XpfException(int errCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
    }

    public int getExceptionCode() {
        return errCode;
    }
}
