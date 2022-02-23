package com.spring.service.location;

import com.spring.model.location.Country;
import com.spring.repositories.jpa.CountryRepoSQL;
import com.spring.repositories.jpa.RegionRepoSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LocationServiceImpl implements LocationService {

    private final CountryRepoSQL countryRepoSQL;
    private final RegionRepoSQL regionRepoSQL;

    @Autowired
    public LocationServiceImpl(CountryRepoSQL countryRepoSQL, RegionRepoSQL regionRepoSQL){
        this.countryRepoSQL = countryRepoSQL;
        this.regionRepoSQL = regionRepoSQL;
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
    public CompletableFuture<List<Country>> getAllCountry(){
        List<Country> countries = countryRepoSQL.findAll();
        return CompletableFuture.completedFuture(countries);
    }

}
