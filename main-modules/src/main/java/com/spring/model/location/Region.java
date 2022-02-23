package com.spring.model.location;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "region_title", unique = true)
    private String region_title;

    @Column(name = "global_location")
    private String global_location;

//    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
//    private Collection<Country> country = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion_title() {
        return region_title;
    }

    public void setRegion_title(String region_title) {
        this.region_title = region_title;
    }

    public String getGlobal_location() {
        return global_location;
    }

    public void setGlobal_location(String global_location) {
        this.global_location = global_location;
    }

//    public Collection<Country> getCountry() {
//        return country;
//    }
//
//    public void setCountry(Collection<Country> country) {
//        this.country = country;
//    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", region_title='" + region_title + '\'' +
                ", global_location='" + global_location + '\'' +
//                ", country=" + country +
                '}';
    }
}