package com.makun.config.shiroredis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @说明：shiro-redis 缓存控制
 * @author makun
 */
@Component
@Qualifier("shiroRedisCacheManager")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ShiroRedisCacheManager implements CacheManager {

    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache = caches.get(name);
        if (cache == null) {
            org.springframework.cache.Cache springCache = cacheManager.getCache(name);
            cache = new ShiroRedisCache(springCache);
            caches.put(name, cache);
        }
        return cache;
    }

}
