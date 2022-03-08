package com.user.model;

//import com.spring.config.Constant;
import com.user.model.form.RegistrationRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class User extends RegistrationRequest {

    @Column
    private Boolean activated = false;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = Constant.MODIFIED_DATE_FIELD)
    @Column(name = "date_modified")
    private Date date_modified;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "authorities")
    private Collection<Authority> authorities = new ArrayList<Authority>();

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
                ", authorities=" + authorities.toString() +
                '}';
    }
}
