package com.farm.common.bootstarp;

import com.farm.product.domain.Product;
import com.farm.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile({"default","local", "dev"})
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {

        if (productRepository.count() > 0) {
            return; // avoid duplicate inserts
        }

        productRepository.save(
                new Product(null, "Tomato", "Vegetable",
                        new BigDecimal("30.00"), true)
        );

        productRepository.save(
                new Product(null, "Potato", "Vegetable",
                        new BigDecimal("25.00"), true)
        );

        productRepository.save(
                new Product(null, "Apple", "Fruit",
                        new BigDecimal("120.00"), true)
        );

        productRepository.save(
                new Product(null, "Banana", "Fruit",
                        new BigDecimal("40.00"), true)
        );
    }
}
