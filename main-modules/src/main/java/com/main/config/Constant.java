package com.main.config;

import org.springframework.beans.factory.annotation.Value;

public class Constant {
    public static final String MODIFIED_DATE_FIELD = "date_modified";
    public static final String DELETE_FLAG_FIELD = "delete_flag";
    public static final String SYNC_FLAG_FIELD = "sync_flag";

//    // Sai số = check dữ liệu trước khoảng thời gian sync tính theo phút (Default 0)
//    public static final Integer CHECK_MODIFIED_ERROR = 0;

    // NOT FOR MODIFY
    public static Integer CHECK_MODIFIED_AFTER_MS;
    public static String EXCEL_DIR;
}