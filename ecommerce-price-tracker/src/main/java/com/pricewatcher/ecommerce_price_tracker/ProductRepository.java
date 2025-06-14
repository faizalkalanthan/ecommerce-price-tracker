package com.pricewatcher.ecommerce_price_tracker.repository;

import com.pricewatcher.ecommerce_price_tracker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
