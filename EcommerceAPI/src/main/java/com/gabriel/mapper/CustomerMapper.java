package com.gabriel.mapper;

import com.gabriel.Entity.CustomersEntity;
import com.gabriel.dto.CustomerResponseDTO;

public class CustomerMapper {

    public static CustomerResponseDTO toDTO(CustomersEntity customersEntity){
        return new CustomerResponseDTO(
                customersEntity.getId()
                , customersEntity.getCustomerName(),
                customersEntity.getEmail()
        );
    }


}

