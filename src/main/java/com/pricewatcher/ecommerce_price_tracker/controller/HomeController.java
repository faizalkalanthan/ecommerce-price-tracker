package com.pricewatcher.ecommerce_price_tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Ecommerce Price Tracker API. Use /api/products for product operations.";
    }
}
