package br.com.ifood.shop.catalog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.ifood.shop.catalog.data.repository.ProductRepository;
import br.com.ifood.shop.catalog.error.DataNotFoundException;
import br.com.ifood.shop.catalog.model.Product;

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
    @Cacheable(cacheNames = "Product", key="#productId")
	public Product findById(Integer productId) throws DataNotFoundException {
		Optional<Product> product = repository.findById(productId);
		
		if (product.isPresent())
			return product.get();
		else
			throw new DataNotFoundException();
	}
}