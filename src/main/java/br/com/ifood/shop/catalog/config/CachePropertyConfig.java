package br.com.ifood.shop.catalog.config;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ifood")
public class CachePropertyConfig {

    private HashMap<String, Integer> cache;

    public HashMap<String, Integer> getCache() {
        return cache;
    }

    public void setCache(HashMap<String, Integer> cache) {
        this.cache = cache;
    }
    
}
