package com.spring.service.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.model.base.ModelSpecifications;
import com.spring.model.user.User;
import com.spring.model.user.UserSearch;
import com.spring.repositories.elastic.UserRepoElastic;
import com.spring.repositories.jpa.UserRepoSQL;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public ElasticSynchronizer(
            UserRepoSQL userRepoSQL,
            UserRepoElastic userRepoElastic,
            ObjectMapper objectMapper
    ){
        this.userRepoSQL = userRepoSQL;
        this.userRepoElastic = userRepoElastic;
        this.objectMapper = objectMapper;
    }

    //    https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
    private UserSearch convertToES(User user){
        return objectMapper.convertValue(user,UserSearch.class);
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
        List<User> users;
        users = userRepoSQL.findAll(ModelSpecifications.getSyncFlag());
        for (User user : users) {
            try {
                userRepoElastic.save(this.convertToES(user));
                user.setSync_flag(true);
                userRepoSQL.save(user);
            } catch (Exception e){e.printStackTrace();}
        }
    }

    private void delete(){
        List<User> users;
        users = userRepoSQL.findAll(ModelSpecifications.getDeleteFlag());
        for (User user : users) {
            userRepoSQL.delete(user);
            userRepoElastic.delete(this.convertToES(user));
        }
    }

//    @Transactional
//    private void setSyncTrue(User user){
//        user.setSync_flag(true);
//    }
}