package com.abmm.b2w.alodjinha.model.enums;

import com.abmm.b2w.alodjinha.R;

public enum ErrorCode {

    BAD_REQUEST(400, R.string.error_bad_request),
    UNAUTHORIZED(401, R.string.error_unauthorized),
    FORBIDDEN(403, R.string.error_forbidden),
    NOT_FOUND(404, R.string.error_not_found),
    METHOD_NOT_ALLOWED(405, R.string.error_method_not_allowed),
    UNKNOWN(0, R.string.error_unknown);

    private int code;
    private int resId;

    ErrorCode(int code, int resId) {
        this.code = code;
        this.resId = resId;
    }

    public int getCode() {
        return code;
    }

    public int getResId() {
        return resId;
    }

    public static ErrorCode get(int code) {
        for (ErrorCode error : ErrorCode.values()) {
            if (error.code == code){
                return error;
            }
        }

        return UNKNOWN;
    }
}