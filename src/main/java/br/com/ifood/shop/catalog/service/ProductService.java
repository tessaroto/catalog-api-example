package br.com.ifood.shop.catalog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.ifood.shop.catalog.data.repository.ProductRepository;
import br.com.ifood.shop.catalog.error.DataNotFoundException;
import br.com.ifood.shop.catalog.model.Product;
import lombok.extern.java.Log;

@Log
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
	 * This method refresh the cache with data from database
	 * @param id The id of product
	 * @return Returns a product 
	 */
    @CachePut(cacheNames = "Product", key="#id")
	public Product refreshCache(Integer id) {
    	Optional<Product> product = repository.findById(id);
    	if (product.isPresent())
			return product.get();
		else
			return null;
	}
}