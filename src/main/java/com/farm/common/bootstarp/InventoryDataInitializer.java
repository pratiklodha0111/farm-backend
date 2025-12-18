package com.farm.common.bootstarp;

import com.farm.inventory.domain.Inventory;
import com.farm.inventory.repository.InventoryRepository;
import com.farm.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
@RequiredArgsConstructor
public class InventoryDataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) {

        if (inventoryRepository.count() > 0) return;

        productRepository.findAll().forEach(product ->
                inventoryRepository.save(
                        new Inventory(null, product.getId(), 100)
                )
        );
    }
}
