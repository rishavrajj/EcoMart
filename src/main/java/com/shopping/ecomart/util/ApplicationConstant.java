package com.shopping.ecomart.util;

import java.util.concurrent.TimeUnit;

public class ApplicationConstant {
    public static final String JWT_KEY = "638CBE3A90E0303BF3808F40F95A7F02A24B4B5D029C954CF553F79E9EF1DC0384BE681C249F1223F6B55AA21DC070914834CA22C8DD98E14A872CA010091ACC";
    public static final String JWT_HEADER = "Authorization";
    public static final long JWT_VALIDITY = TimeUnit.MINUTES.toMillis(30L);
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;

    public static enum StatusCode {
        DATA_FOUND,
        DATA_NOT_FOUND,
        RESOURCE_CREATED,
        RESOURCE_UPDATED,
        RESOURCE_DELETED,
        UPDATE_FAILED;

    }
}
