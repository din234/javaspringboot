package com.main.service.location;

import com.main.model.location.Country;
import com.main.model.location.LocationSearch;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LocationService {
    void addCountry();
    void addRegion();

    CompletableFuture<List<Country>> getCountryByRegion(String region);
    CompletableFuture<List<LocationSearch>> getAllCountry();
//    void checkRegion();
}