package com.main.service.test;//package com.spring.service.test;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CacheService {
//    Logger logger = LoggerFactory.getLogger(CacheService.class);
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    public String getCache(){
//        Cache user = this.cacheManager.getCache("user");
//
//        logger.info(String.valueOf(user));
//        return null;
//    }
//}
