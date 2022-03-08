package com.main.model.base;

import com.main.config.Constant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ModelImpl implements Model {

    @Column(name = Constant.SYNC_FLAG_FIELD)
    protected Boolean sync_flag = false;

    @Column(name = Constant.DELETE_FLAG_FIELD)
    protected Boolean delete_flag = false;


    @Override
    public Boolean getSync_flag() {
        return this.sync_flag;
    }

    @Override
    public Boolean getDelete_flag() {
        return this.delete_flag;
    }

    @Override
    public void setSync_flag(Boolean sync_flag) {
        this.sync_flag = sync_flag;
    }

    @Override
    public void setDelete_flag(Boolean delete_flag) {
        this.delete_flag = delete_flag;
    }
}
