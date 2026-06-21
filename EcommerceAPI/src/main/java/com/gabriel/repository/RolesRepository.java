package com.gabriel.repository;

import com.gabriel.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends  JpaRepository<RolesEntity,Long> {
}

