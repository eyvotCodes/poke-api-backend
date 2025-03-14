package com.eyvot.pokeapi.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableCaching
public class WebCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(Constants.CACHE_POKEAPI_SCOPE);
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(Constants.CACHE_EXPIRE_MINUTES_AFTER_WRITE, TimeUnit.MINUTES)
                .expireAfterAccess(Constants.CACHE_EXPIRE_MINUTES_AFTER_READ, TimeUnit.MINUTES)
                .maximumSize(Constants.CACHE_ENTRY_LIMIT)
        );
        return cacheManager;
    }

}
