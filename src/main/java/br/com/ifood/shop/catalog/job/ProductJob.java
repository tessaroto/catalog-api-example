package br.com.ifood.shop.catalog.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.ifood.shop.catalog.service.ProductService;
import net.javacrumbs.shedlock.core.SchedulerLock;

@Component
public class ProductJob {
	
	@Autowired
	ProductService productService;
	
	@Scheduled(fixedDelayString = "#{${ifood.job.product.interval} * 1000}")
	@SchedulerLock(name = "scheduledTaskName")
	public void scheduledTask() {
	   System.out.println("do something.......");
	   productService.refreshCache(1);
	}
	
}
