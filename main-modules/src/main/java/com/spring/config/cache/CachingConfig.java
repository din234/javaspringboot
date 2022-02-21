package com.spring.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport {
    private static Logger logger = LoggerFactory.getLogger(CachingConfig.class);


//    @Bean
//    @Override
//    public CacheManager cacheManager(){
//        return null;
//    }
//
//    @Override
//    public CacheResolver cacheResolver(){
//        return new CustomCacheResolver();
//    }
}
