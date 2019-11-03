package br.com.ifood.shop.catalog.service;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.ifood.shop.catalog.data.repository.ProductRepository;
import br.com.ifood.shop.catalog.error.DataNotFoundException;
import br.com.ifood.shop.catalog.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Autowired
	ProductRepository repository;
	
	/**
	 * This method find the product by id
	 * @param id The id of product
	 * @return Returns a product 
	 * @throws DataNotFoundException 
	 */
    @Cacheable(cacheNames = "Product", key="#id")
	public Product findById(Integer id) throws DataNotFoundException {
		Optional<Product> product = repository.findById(id);
		
		if (product.isPresent())
			return product.get();
		else
			throw new DataNotFoundException();
	}
    
    /**
	 * This method create or change a product
	 * @param product The object of product
	 * @return Returns a product 
	 */
    @CachePut(cacheNames = "Product", key="#product.id")
	public Product save(Product product) {
    	System.out.println(product.toString());
		return repository.save(product);
	}
    
    /**
	 * This method delete the product by id
	 * @param id The id of product
	 * @return Returns a product 
	 * @throws DataNotFoundException 
	 */
    @CacheEvict(cacheNames = "Product", key="#product.id")
	public Product delete(Integer id) throws DataNotFoundException {
		Optional<Product> product = repository.findById(id);
		
		if (product.isPresent())
			return product.get();
		else
			throw new DataNotFoundException();
	}
    
	/**
	 * This method refresh the cache of product
	 * @param id The object of product
	 * @return Returns a product 
	 */
    @CachePut(cacheNames = "Product", key="#product.id")
	public Product refreshCache(Product product) {
    	return product;
	}
    
    /**
	 * This method find products by modifedDate 
	 * @param modifedDate The modifedDate of product
	 * @return Returns the greater modifedDate
	 */
	public void visitByModifedDate(Date modifedDate, Consumer<Product> action) {
		
		Iterable<Product> items;
		
		if (modifedDate == null)
			items = repository.findAll();
		else
			items = repository.findByModifedDateGreaterThan(modifedDate);
		
		items.forEach(product->{
			action.accept(product);
		});
		
	}

}