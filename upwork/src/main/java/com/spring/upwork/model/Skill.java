package com.spring.upwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Skill {
    @Id
    private Long uid;
//    private String name;
    private String prettyName;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "uid=" + uid +
//                ", name='" + name + '\'' +
                ", prettyName='" + prettyName + '\'' +
                '}';
    }
}
