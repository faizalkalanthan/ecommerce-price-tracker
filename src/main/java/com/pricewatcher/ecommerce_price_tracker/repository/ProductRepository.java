package com.pricewatcher.ecommerce_price_tracker.repository;

import com.pricewatcher.ecommerce_price_tracker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom queries later
}
