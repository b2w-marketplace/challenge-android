package com.abmm.b2w.alodjinha.utils;

import com.abmm.b2w.alodjinha.BuildConfig;

public class Constants {

    public static class LodjinhaServer {
        public static final String BASE_URL = "https://alodjinha.herokuapp.com/";
    }

    public static class General {
        public static final int FIRST_POSITION = 0;
        public static final int EMPTY_LIST = 0;

        public static final int BANNER_DURATION_TIMER = 5000; // 5 secs
    }

    public static class Paging {
        public static final int DEFAULT_FIRST_ELEMENT = 0;
        public static final int DEFAULT_PAGE_SIZE = 3;
    }

    public static class Keys {
        public static final String CATEGORY_DATA = BuildConfig.APPLICATION_ID + "CATEGORY_DATA";
        public static final String PRODUCT_DATA = BuildConfig.APPLICATION_ID + "PRODUCT_DATA";
    }

}