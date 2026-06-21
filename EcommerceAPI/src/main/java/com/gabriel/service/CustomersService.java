package com.gabriel.service;

import com.gabriel.Entity.CustomersEntity;
import com.gabriel.dto.CustomerRequestDTO;
import com.gabriel.dto.CustomerResponseDTO;
import com.gabriel.exception.ConflictExeption;
import com.gabriel.exception.ForbiddenExeption;
import com.gabriel.exception.NotFoundExeption;
import com.gabriel.mapper.CustomerMapper;
import com.gabriel.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomersService {

    private final CustomersRepository customersRepository;
    private final PasswordEncoder passwordEncoder;



    public List<CustomerResponseDTO> findAll(){
        return customersRepository.findAll().stream().map(CustomerMapper::toDTO).toList();
    }


    public List<CustomerResponseDTO> findAllByPage(int page, int customers){
        return customersRepository.findAll(PageRequest.of(page, customers)).stream().map(CustomerMapper::toDTO).toList();

    }


    public CustomerResponseDTO findById(Long id){
      return customersRepository.findById(id).map(CustomerMapper::toDTO).orElseThrow(() ->new NotFoundExeption("Customer "+id+ " not found"));
    }


    public void save(CustomerRequestDTO newCustomer){
        if(!Objects.equals(newCustomer.password(), newCustomer.confirmPassword())){
            throw new ForbiddenExeption("Please, make sure the password and confirm password match");
        }

        CustomersEntity customers= CustomersEntity.builder()
                .customerName(newCustomer.name())
                .email(newCustomer.email())
                .password(passwordEncoder.encode(newCustomer.password()))
                .build();

        if(customersRepository.existsByEmail(customers.getEmail())){
            throw new ConflictExeption("The email '"+ customers.getEmail() + "' already exists");
            }

        customersRepository.save(customers);
        }


    public void update(Long id, CustomerRequestDTO customer){
        var customers= customersRepository.findById(id).orElseThrow(() -> new NotFoundExeption("Customer "+id+ " not found"));

        customers.setCustomerName(customer.name());
        customers.setEmail(customer.email());
        customers.setPassword(passwordEncoder.encode(customer.password()));

        if(!Objects.equals(customer.password(), customer.confirmPassword())){
            throw new ForbiddenExeption("Please, make sure the password and confirm password match");
        }

        customersRepository.save(customers);
    }



    public void delete(Long id){
        customersRepository.findById(id).orElseThrow(() -> new NotFoundExeption("Customer "+id+ " not found"));
        customersRepository.deleteById(id);
    }

}

