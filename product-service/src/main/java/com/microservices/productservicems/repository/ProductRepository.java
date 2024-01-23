package com.microservices.productservicems.repository;


import com.microservices.productservicems.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
