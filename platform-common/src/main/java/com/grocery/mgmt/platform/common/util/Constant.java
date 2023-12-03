package com.grocery.mgmt.platform.common.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;


@UtilityClass
public final class Constant {

    // Rest Status code constants
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int CONFLICT = 409;
    public static final int NOT_FOUND = 404;
    public static final int UNAUTHORIZED = 401;
    public static final int BAD_REQUEST = 400;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;

    // API URL CONSTANT
    public static final String V1_API_VERSION = "/v1";
    public static final String BASE_URL = "/api" + V1_API_VERSION;
    public static final String USER_URL = BASE_URL + "/user";
    public static final String ADMIN_URL = BASE_URL + "/admin";
    public static final String GI_URL = ADMIN_URL + "/grocery-items";
    public static final String ORDER_URL = USER_URL + "/order";

    public static final String CROSS_ORIGIN_VALUE = "*";

    public static final String ENTITY_PACKAGE = "com.grocery.mgmt.platform.common.entity";

    public static final String PROFILE_DEV = "dev";

    public static final String EMPTY_STRING = "";
    public static final String ALL_STRING = "All";

    public static final UUID DEFAULT_USERID = new UUID(0,0);
}
