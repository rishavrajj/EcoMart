package com.shopping.ecomart.util;

public class ApplicationConstant {
    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";
    public static enum StatusCode {
        DATA_FOUND,
        DATA_NOT_FOUND,
        RESOURCE_CREATED,
        RESOURCE_UPDATED,
        RESOURCE_DELETED,
        UPDATE_FAILED;
    }
}
