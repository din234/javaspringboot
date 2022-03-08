package com.main.model.base;

public interface Model {
    Boolean getSync_flag();
    Boolean getDelete_flag();

    void setSync_flag(Boolean sync_flag);
    void setDelete_flag(Boolean delete_flag);
}
