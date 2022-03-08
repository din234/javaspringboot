package com.main.service.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.model.base.ModelSpecifications;
import com.main.model.location.Country;
import com.main.repositories.elastic.UserRepoElastic;
import com.main.repositories.jpa.UserRepoSQL;
import com.main.model.location.LocationSearch;
import com.spring.model.user.User;
import com.spring.model.user.UserSearch;
import com.main.repositories.elastic.LocationRepoElastic;
import com.main.repositories.jpa.CountryRepoSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


/**
 * Service/Scheduler dùng để sync dữ liệu giữa MySQL và Elasticsearch
 */
@Service
public class ElasticSynchronizer {
    private static Logger logger = LoggerFactory.getLogger(ElasticSynchronizer.class);

    private final UserRepoSQL userRepoSQL;
    private final UserRepoElastic userRepoElastic;

    private final CountryRepoSQL countryRepoSQL;
    private final LocationRepoElastic locationRepoElastic;

    private final ObjectMapper objectMapper;

    @Autowired
    public ElasticSynchronizer(
            UserRepoSQL userRepoSQL,
            UserRepoElastic userRepoElastic,
            CountryRepoSQL countryRepoSQL,
            LocationRepoElastic locationRepoElastic,
            ObjectMapper objectMapper
    ){
        this.userRepoSQL = userRepoSQL;
        this.userRepoElastic = userRepoElastic;

        this.countryRepoSQL = countryRepoSQL;
        this.locationRepoElastic = locationRepoElastic;

        this.objectMapper = objectMapper;
    }

    //    https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
    private UserSearch convertUserToES(User user){
        return objectMapper.convertValue(user,UserSearch.class);
    }

    private LocationSearch convertLocationToES(Country country){
        return objectMapper.convertValue(country, LocationSearch.class);
    }

    // update sau mot khoang thoi gian (theo form crontab)
    @Scheduled(cron = "0 */${sync.min} * * * *")
    public void sync(){
        String start = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this.add();
        this.delete();
        String end = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//        logger.info("Synced Elastic from: " + start + " to " + end);
        logger.info("Triggered");
    }

    private void add(){
        addUser();
        addLocation();
    }

    private void delete(){
        List<User> users;
        users = userRepoSQL.findAll(ModelSpecifications.getDeleteFlag());
        for (User user : users) {
            userRepoSQL.delete(user);
            userRepoElastic.delete(this.convertUserToES(user));
        }
    }

    @Async
    private void addUser(){
        List<User> users;
        users = userRepoSQL.findAll(ModelSpecifications.getSyncFlag());
        for (User user : users) {
            try {
                userRepoElastic.save(this.convertUserToES(user));
                user.setSync_flag(true);
                userRepoSQL.save(user);
            } catch (Exception e){e.printStackTrace();}
        }
    }

    @Async
    private void addLocation(){
        List<Country> countries;
        countries = countryRepoSQL.findAll(ModelSpecifications.getSyncFlag());
        for (Country country : countries) {
            try {
                locationRepoElastic.save(this.convertLocationToES(country));
                country.setSync_flag(true);
                countryRepoSQL.save(country);
            } catch (Exception e){e.printStackTrace();}
        }
    }

//    @Transactional
//    private void setSyncTrue(User user){
//        user.setSync_flag(true);
//    }
}