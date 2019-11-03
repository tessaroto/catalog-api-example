package br.com.ifood.shop.catalog.data.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import br.com.ifood.shop.catalog.model.Product;

public interface ProductRepository 
	extends CrudRepository<Product, Integer> {
	
	public Iterable<Product> findByModifedDateGreaterThan(Date modifedDate);
}