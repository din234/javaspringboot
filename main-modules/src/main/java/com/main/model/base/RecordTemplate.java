package com.main.model.base;

import org.apache.poi.ss.usermodel.Row;

public interface RecordTemplate<T> {
    public T setMapping();
    public T getRecord(Row row);
}


// default
