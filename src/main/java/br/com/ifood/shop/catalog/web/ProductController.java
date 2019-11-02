package br.com.ifood.shop.catalog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifood.shop.catalog.error.DataNotFoundException;
import br.com.ifood.shop.catalog.model.Product;
import br.com.ifood.shop.catalog.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	/**
	 * This method return a product.
	 * @param id The id of product.
	 * @return Return the product.
	 * @throws DataNotFoundException 
	 */
	@ApiOperation(value = "Get a product by id")
    @GetMapping("/product/{productId}")
    public Product findById(@PathVariable Integer productId) throws DataNotFoundException {
    	
    	return productService.findById(productId);
    }
	
	/**
	 * This method create a product.
	 * @param id The id of product.
	 * @return Return the product.
	 */
	@ApiOperation(value = "Save product")
	@PostMapping("/product")
    public Product save(@RequestBody Product product){
    	
    	return productService.save(product);
    }
	
	/**
	 * This method create a product.
	 * @param id The id of product.
	 * @return Return the product.
	 * @throws DataNotFoundException 
	 */
	@ApiOperation(value = "Save product")
	@DeleteMapping("/product/{productId}")
    public Product save(@PathVariable Integer productId) throws DataNotFoundException {
    	
    	return productService.delete(productId);
    }
	
	
}
