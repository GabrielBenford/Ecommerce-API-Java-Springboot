package com.gabriel.repository;

import com.gabriel.Entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomersRepository extends JpaRepository<CustomersEntity, Long> {

    Optional<CustomersEntity> findByEmail(String email);
    boolean existsByEmail(String email);

}

