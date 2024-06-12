package com.example.practice.mapper;

import com.example.practice.entity.Product;
import com.example.practice.payload.ProductPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product payloadToProduct(ProductPayload productPayload);
}
