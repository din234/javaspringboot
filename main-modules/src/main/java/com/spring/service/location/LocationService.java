package com.spring.service.location;

import com.spring.model.location.Country;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LocationService {
    void addCountry();
    void addRegion();

    CompletableFuture<List<Country>> getCountryByRegion(String region);
    CompletableFuture<List<Country>> getAllCountry();
//    void checkRegion();
}
