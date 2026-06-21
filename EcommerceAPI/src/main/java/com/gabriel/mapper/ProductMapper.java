package com.gabriel.mapper;

import com.gabriel.Entity.ProductsEntity;
import com.gabriel.dto.ProductsResponseDTO;

public class ProductMapper {

    public static ProductsResponseDTO toDTO(ProductsEntity productsEntity){
        return new ProductsResponseDTO(
                productsEntity.getId(),
                productsEntity.getName(),
                productsEntity.getPrice(),
                productsEntity.getStock()
        );

    }
}

