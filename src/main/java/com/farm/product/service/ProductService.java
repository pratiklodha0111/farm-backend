package com.farm.product.service;

import com.farm.product.domain.Product;
import com.farm.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<Product> getActiveProducts() {
        return repository.findByActiveTrue();
    }
}
