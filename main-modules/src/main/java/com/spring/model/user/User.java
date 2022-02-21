package com.spring.model.user;

import com.spring.config.Constant;
import com.spring.model.jpa.Authority;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class User extends RegistrationRequest {

    @Column
    private Boolean activated = false;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constant.MODIFIED_DATE_FIELD)
    private Date date_modified;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "authorities")
    private Collection<Authority> authorities = new ArrayList<>();

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateCreated=" + dateCreated +
                ", date_modified=" + date_modified +
                ", authorities=" + authorities +
                '}';
    }
}