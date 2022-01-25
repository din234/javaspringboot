package com.spring.model;

import java.time.LocalDate;
import java.util.Map;

public interface ExportUser extends Map<String,Object> {
    // Get
    String getName();

    String getAddressline1();
    String getAddressLine2();
    LocalDate getDob();

    ExportUser setName();
    ExportUser setAddressLine1(String addr1);
    ExportUser setAddressLine2(String addr2);
}
