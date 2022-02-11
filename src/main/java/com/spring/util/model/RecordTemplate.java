package com.spring.util.model;

import org.apache.poi.ss.usermodel.Row;

public interface RecordTemplate<T> {
    public T setMapping();
    public T getRecord(Row row);
}


// default
