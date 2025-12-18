package com.farm.order.controller;

import com.farm.order.domain.Order;
import com.farm.order.dto.CreateOrderRequest;
import com.farm.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order placeOrder(@RequestBody  @Valid CreateOrderRequest request) {
        return service.placeOrder(request);
    }
}
