package com.farm.inventory.service;

import com.farm.common.exception.InsufficientStockException;
import com.farm.common.exception.ResourceNotFoundException;
import com.farm.inventory.domain.Inventory;
import com.farm.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * Reserve stock for an order.
     * This method MUST be transactional.
     */
    @Transactional
    public void reserveStock(Long productId, int quantity) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory not found for product " + productId)
                );

        if (inventory.getAvailableQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock available");
        }


        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - quantity
        );

        inventoryRepository.save(inventory);
    }
}
