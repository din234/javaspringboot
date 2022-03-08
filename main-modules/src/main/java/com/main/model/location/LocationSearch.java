package com.main.model.location;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "location")
public class LocationSearch {
    @Id
    private String id;

    @Field(name = "country_title")
    private String country_title;

    @Field(name = "region", type = FieldType.Object)
    private Region region;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "LocationSearch{" +
                "id='" + id + '\'' +
                ", country_title='" + country_title + '\'' +
                ", region=" + region +
                '}';
    }
}
