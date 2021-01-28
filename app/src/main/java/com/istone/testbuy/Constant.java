package com.istone.testbuy;

/**
 * Created by Ruansu
 * on 2020/7/27 5:30 PM
 */
public class Constant {
    public static final String CHANNEL_CODE = "HQ01S116";

    public static class Http {
        public static final String BASE_URL = "https://api.ips-new2.realclub.cn/";
    }

    public static class SPKey {
        public static final String AGREED = "agreed";
        public static final String UID_DATE = "uidDate";
        public final static String USER_INFO = "userInfo";
        public final static String VERSION_NAME = "versionName";
        public final static String VERSION_CODE = "versionCode";
        public static final String DEVICE_TOKEN = "deviceToken";
        public static final String HOME_ICON_LINK = "homeIconLink";
        public static final String GOODS_LEVEL_1 = "level1_classify_data";
        public static final String SEARCH_HISTORY_LIST = "searchHistoryList";
        public static final String RETURN_MONEY_REASON = "returnMoneyReason";
        public static final String RETURN_GOODS_REASON = "returnGoodsReason";
        public static final String THEME_INIT_DATA = "themeInitData";
    }

    public static class SKType
    {
        public static final int SKTYPE_ALL = 0;
        public static final int SKTYPE_FIRST = 1;
        public static final int SKTYPE_END = 2;
    }
}
