package com.gabriel.service;
import com.gabriel.Entity.ProductsEntity;
import com.gabriel.dto.ProductRequestDTO;
import com.gabriel.dto.ProductsResponseDTO;
import com.gabriel.mapper.ProductMapper;
import com.gabriel.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;

    public List<ProductsResponseDTO> findAllProducts(){
        return productsRepository.findAll().stream().map(ProductMapper::toDTO).toList();
    }




    public void createProduct(ProductRequestDTO products){
        ProductsEntity productsEntity= ProductsEntity.builder()
                .name(products.name())
                .price(products.price())
                .stock(products.stock())
                .build();
        productsRepository.save(productsEntity);
    }
}

