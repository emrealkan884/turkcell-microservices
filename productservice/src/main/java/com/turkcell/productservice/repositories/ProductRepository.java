package com.turkcell.productservice.repositories;

import com.turkcell.productservice.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, String> {
  @Query("{'inventoryCode': ?0 }")
  Product findByInventoryCodeQuery(String inventoryCode);

  Product findByInventoryCode(String inventoryCode);
}
