package br.com.ifood.shop.catalog.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.ifood.shop.catalog.job.model.Job;
import br.com.ifood.shop.catalog.job.service.JobService;
import br.com.ifood.shop.catalog.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;

@Slf4j
@Component
public class ProductJob {
   
	@Autowired
	private JobService jobService;
	
	@Autowired
	ProductService productService;

	@Scheduled(fixedDelayString = "#{${ifood.job.product.interval} * 1000}")
	@SchedulerLock(name = "productUpdate")
	public void productUpdateTask() {

		log.info("Starting the product job");
		
		Job job = jobService.findByName("Product");
		
		if (job.getLastDate() != null)
			log.info("Last date {}", job.getLastDate().toString());
		
		productService.visitByModifedDate(job.getLastDate(), product -> {
			log.info("Updating cache of product id {}", product.getId());
			productService.refreshCache(product);
			job.setLastDate(product.getModifedDate());
		});
		
		jobService.save(job);
		
		log.info("Finished the product job");
	}
}
