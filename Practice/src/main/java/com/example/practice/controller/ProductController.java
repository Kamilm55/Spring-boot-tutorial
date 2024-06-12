package com.example.practice.controller;

import com.example.practice.entity.Product;
import com.example.practice.mapper.ProductMapper;
import com.example.practice.payload.ProductPayload;
import com.example.practice.repository.ProductRepository;
import com.example.practice.response.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("redis-cache/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    @Cacheable(value = "product", key = "#id") // springframework.cache.annotation.Cacheable
    public Product getProductById(@PathVariable long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with id:" + id));
    }

    @PostMapping
    public BaseResponse<Product> insertProduct(@RequestBody ProductPayload productPayload) {
        Product product = ProductMapper.INSTANCE.payloadToProduct(productPayload);

        return BaseResponse.success(productRepository.save(product));
    }

    @PostMapping
    public BaseResponse<Product> insert100kProduct(@RequestBody ProductPayload productPayload) {
        Product product = ProductMapper.INSTANCE.payloadToProduct(productPayload);

        return BaseResponse.success(productRepository.save(product));
    }

}
