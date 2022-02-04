package com.spring.model.jwt;

import java.time.LocalDate;
import java.util.Map;

/**
 * Trả token cho người dùng account
 */
public class JwtResponse {
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

//    public static interface ExportUser extends Map<String,Object> {
//        // Get
//        String getName();
//
//        String getAddressline1();
//        String getAddressLine2();
//        LocalDate getDob();
//
//        ExportUser setName();
//        ExportUser setAddressLine1(String addr1);
//        ExportUser setAddressLine2(String addr2);
//    }
}
