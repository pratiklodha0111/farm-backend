package com.farm.product.controller;

import com.farm.product.domain.Product;
import com.farm.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<Product> getProducts() {
        return service.getActiveProducts();
    }
}
