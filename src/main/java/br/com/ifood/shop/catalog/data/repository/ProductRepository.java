package br.com.ifood.shop.catalog.data.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ifood.shop.catalog.model.Product;

public interface ProductRepository 
	extends CrudRepository<Product, Integer> {
}