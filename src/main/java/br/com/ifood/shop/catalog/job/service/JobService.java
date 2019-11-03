package br.com.ifood.shop.catalog.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import br.com.ifood.shop.catalog.job.model.Job;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobService {
	
	@Autowired
	private RedisTemplate<String, Job> template;

	public Job findByName(String name) {
		
		Job job = null;
		try {
			job = template.opsForValue().get(String.format("Job:%s", name));
		}
		catch (Exception e) {
			log.error("Error when tryed get data of {} job", name, e);
		}
		
		if (job == null)
			job = new Job(name);
		
		return job;
	}
	
	public void save(Job job) {
		template.opsForValue().set(String.format("Job:%s", job.getName()), job);
	}
}
