package com.gabriel.repository;

import com.gabriel.Entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {



}

