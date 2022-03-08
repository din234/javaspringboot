package com.main.service.location;

import com.main.model.location.Country;
import com.main.model.location.LocationSearch;
import com.main.repositories.elastic.LocationRepoElastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LocationServiceImpl implements LocationService {

//    private final CountryRepoSQL countryRepoSQL;
//    private final RegionRepoSQL regionRepoSQL;

    private final LocationRepoElastic locationRepoElastic;

    @Autowired
    public LocationServiceImpl(LocationRepoElastic locationRepoElastic){
        this.locationRepoElastic = locationRepoElastic;
    }


    @Override
    public void addCountry() {

    }

    @Override
    public void addRegion() {

    }

    @Async
    public CompletableFuture<List<Country>> getCountryByRegion(String region){
//        return CompletableFuture.completedFuture();
        return null;
    }

    @Async
    @Override
    public CompletableFuture<List<LocationSearch>> getAllCountry(){
        List<LocationSearch> countries = new ArrayList<>();
        locationRepoElastic.findAll().forEach((c) -> {
            countries.add(c);
        });
        return CompletableFuture.completedFuture(countries);
    }

    @Async
    public List<LocationSearch> getLocations(){
        List<LocationSearch> locations = new ArrayList<>();
        locationRepoElastic.findAll().forEach((c) -> {
            locations.add(c);
        });
        return locations;
    }

}
