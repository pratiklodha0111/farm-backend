package com.farm.order.service;

import com.farm.common.exception.ResourceNotFoundException;
import com.farm.inventory.service.InventoryService;
import com.farm.order.domain.*;
import com.farm.order.dto.CreateOrderRequest;
import com.farm.order.repository.OrderRepository;
import com.farm.product.domain.Product;
import com.farm.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;

    @Transactional
    public Order placeOrder(CreateOrderRequest request) {
        System.out.println("OrderService.placeOrder"+request.getProductId());
        // 1️⃣ Validate product
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found")
                );

        // 2️⃣ Reserve inventory (may fail)
        inventoryService.reserveStock(
                product.getId(),
                request.getQuantity()
        );

        // 3️⃣ Create order item
        OrderItem item = new OrderItem(
                null,
                product.getId(),
                request.getQuantity(),
                product.getPricePerUnit()
        );

        // 4️⃣ Create order aggregate
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.markCreated();
        order.getItems().add(item);

        BigDecimal total =
                product.getPricePerUnit()
                        .multiply(BigDecimal.valueOf(request.getQuantity()));

        order.setTotalAmount(total);

        // 5️⃣ Persist order
        return orderRepository.save(order);
    }
}
