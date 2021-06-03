package com.atlavik.shoppingcart.repository;

import com.atlavik.shoppingcart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Long> {
    //Optional<Product>  findByProductIdAndShopping(String Id, Long shoppingcart_id);
}
