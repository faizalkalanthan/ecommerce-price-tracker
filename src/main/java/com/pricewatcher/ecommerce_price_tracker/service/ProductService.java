package com.pricewatcher.ecommerce_price_tracker.service;

import com.pricewatcher.ecommerce_price_tracker.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
