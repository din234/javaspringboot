package com.spring.model;

import com.spring.util.Gender;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

public class Students {

    private Long id;
    private String name;
    private String email;
    private String address_line1;
    private String address_line2;
    private LocalDate dob;
    private Gender gender;

    public Students(){}
    public Students(
            Long id,
            String name,
            String email,
            String address_line1,
            String address_line2,
            LocalDate dob,
            Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.dob = dob;
        this.gender = gender;
    }
    public Students(
            String name,
            String email,
            String address_line1,
            String address_line2,
            LocalDate dob,
            Gender gender) {
        this.name = name;
        this.email = email;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.dob = dob;
        this.gender = gender;
    }

    /*
    * Getter and setter down here
    * Api sẽ tự độc chạy cá dòng lệnh ở dưới
    * Qua annotation
    * */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address_line1='" + address_line1 + '\'' +
                ", address_line2='" + address_line2 + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                '}';
    }
}
