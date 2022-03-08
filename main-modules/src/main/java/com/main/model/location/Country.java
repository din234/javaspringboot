package com.main.model.location;

import com.main.model.base.ModelImpl;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country extends ModelImpl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "country_title", unique = true)
    private String country_title;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne()
    @JoinColumn(name ="region_id", nullable = false)
    private Region region = new Region();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry_title() {
        return country_title;
    }

    public void setCountry_title(String country_title) {
        this.country_title = country_title;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country_title='" + country_title + '\'' +
                ", region=" + region +
                '}';
    }
}
