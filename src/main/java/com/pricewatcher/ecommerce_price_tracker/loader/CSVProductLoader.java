package com.pricewatcher.ecommerce_price_tracker.loader;

import com.pricewatcher.ecommerce_price_tracker.model.Product;
import com.pricewatcher.ecommerce_price_tracker.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Component
public class CSVProductLoader {

    private static final Logger logger = LoggerFactory.getLogger(CSVProductLoader.class);

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void loadProductsFromCSV() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("Asosmenfashion.csv"),
                StandardCharsets.UTF_8
            ))
        ) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",", -1); // -1 to include trailing empty fields

                if (fields.length >= 10) {
                    Product product = new Product();
                    product.setProductId(parseLong(fields[0]));
                    product.setBrandName(fields[1]);
                    product.setTitle(fields[2]);
                    product.setCurrentPrice(parseBigDecimal(fields[3]));
                    product.setPreviousPrice(parseBigDecimal(fields[4]));
                    product.setRrp(parseBigDecimal(fields[5]));
                    product.setCurrency(fields[6]);
                    product.setProductCode(parseLong(fields[8]));
                    product.setProductType(fields[9]);

                    productRepository.save(product);
                }
            }

            logger.info("✅ CSV import complete.");
        } catch (Exception e) {
            logger.error("❌ Failed to import products from CSV", e);
        }
    }

    private BigDecimal parseBigDecimal(String s) {
        try {
            return (s == null || s.isEmpty()) ? null : new BigDecimal(s);
        } catch (Exception e) {
            return null;
        }
    }

    private Long parseLong(String s) {
        try {
            return (s == null || s.isEmpty()) ? null : Long.parseLong(s);
        } catch (Exception e) {
            return null;
        }
    }
}
