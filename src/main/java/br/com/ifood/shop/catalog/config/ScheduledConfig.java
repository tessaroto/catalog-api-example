package br.com.ifood.shop.catalog.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@Configuration
@EnableScheduling
@ConditionalOnProperty(prefix = "ifood.job", name = "enabled")
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ScheduledConfig {
	
	@Bean
	public LockProvider lockProvider(RedisConnectionFactory connectionFactory) {
	    return new RedisLockProvider(connectionFactory);
	}
	
}
