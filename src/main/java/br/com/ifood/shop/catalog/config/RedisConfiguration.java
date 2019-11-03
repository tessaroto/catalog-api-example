package br.com.ifood.shop.catalog.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

	@Autowired
	private CachePropertyConfig cachePropertyConfig;
	
	@Autowired
	private LettuceConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        
        return template;
    }
       
    @Bean
    public CacheManager redisCacheManager() {
    	
        RedisSerializationContext.SerializationPair<Object> jsonSerializer = 
        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
        
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<String, RedisCacheConfiguration>();
        
        for(Entry<String, Integer> cache : cachePropertyConfig.getCache().entrySet()) {
        	cacheConfigurations.put(cache.getKey(), RedisCacheConfiguration.defaultCacheConfig()
					.entryTtl(Duration.ofSeconds(cache.getValue()))
					.serializeValuesWith(jsonSerializer));
    	}
        
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
    
}
