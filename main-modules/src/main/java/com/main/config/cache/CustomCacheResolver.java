package com.spring.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomCacheResolver implements CacheResolver {
    private static Logger logger = LoggerFactory.getLogger(CustomCacheResolver.class);

    @Autowired
    CacheManager cacheManager;

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        logger.info("OKOKOKOOK");
        Cache cache = cacheManager.getCache("user");
        return null;
    }
}
