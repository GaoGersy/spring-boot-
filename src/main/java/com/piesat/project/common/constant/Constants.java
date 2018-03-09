package com.piesat.project.common.constant;

public interface Constants {
    int SUCCESS = 100;
    int PASSWORD_ERROR = 101;
    int NOT_REGISTERED = 102;
    int OPEN_ID_NOT_FOUND = 103;
    int REGISTERED = 104;
    int REGISTER_ERROR = 105;
    int INSERT_SUCCESS = 200;
    int INSERT_ERROR = 201;
    int USER_NOT_EXISTS_ERROR = 211;
    int KEY_WORD_EXISTS = 303;

    String[] IMAGE_TYPE = {"jpg","jpeg","png","bmp"};
    String MEDIA = "image/media/";

    interface ShiroKey{
        String TOKEN = "token";
        String USER_INFO = "userInfo";
    }

    class WeiXin {
        public static String TOKEN;
        public static String ACCESS_TOKEN;
        public static String REFRESH_TOKEN;
        public static long ACCESS_TOKEN_GET_TIME;
        public static long TOKEN_GET_TIME;
        public static long REFRESH_TOKEN_GET_TIME;
    }

    class MsgType {
        public static final int TEXT_MSG = 1;
        public static final int MEDIA_MSG = 2;
        public static final int IMAGE_TEXT_MSG = 3;
    }

    class AddMsgResult {
        public static final int KEY_WORD_EXISTS = -100;
        public static final int KEY_WORD_NOT_EXISTS = -101;
        public static final int SUCCESS = 1;
        public static final int FAILED = -1;
    }

}
