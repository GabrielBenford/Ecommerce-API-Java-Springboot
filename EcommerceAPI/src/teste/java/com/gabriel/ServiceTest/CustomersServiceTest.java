package com.gabriel.ServiceTest;

import com.gabriel.Entity.CustomersEntity;
import com.gabriel.dto.CustomerRequestDTO;
import com.gabriel.dto.CustomerResponseDTO;
import com.gabriel.exception.ConflictExeption;
import com.gabriel.exception.ForbiddenExeption;
import com.gabriel.exception.NotFoundExeption;
import com.gabriel.repository.CustomersRepository;
import com.gabriel.service.CustomersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomersServiceTest {

    @InjectMocks
    CustomersService customersService;
    @Mock
    CustomersRepository customersRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Test
    void findAllTest() {

        CustomersEntity customersEntity= CustomersEntity.builder()
                .id(1L)
                .customerName("Gabriel")
                .email("lourii@gmail.com")
                .password("1234")
                .build();

        Mockito.when(customersRepository.findAll()).thenReturn(Collections.singletonList(customersEntity));
        List<CustomerResponseDTO> customers=customersService.findAll();
        System.out.println(customers);

        assertNotNull(customers);
        assertEquals("Gabriel", customers.get(0).name());
        assertEquals("lourii@gmail.com", customers.get(0).email());
    }


    @Test
    void testFindById() {
        CustomersEntity customersEntity= CustomersEntity.builder()
                .id(1L)
                .customerName("Gabriel")
                .email("lourii@gmail.com")
                .password("1234")
                .build();
        Mockito.when(customersRepository.findById(1L)).thenReturn(java.util.Optional.of(customersEntity));
        CustomerResponseDTO customer=customersService.findById(1L);
        System.out.println(customer);
        assertNotNull(customer);
    }

    @Test
    public void testingPostCustomers() {

        //Mocking the password encoder to return a specific encoded password
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encoded");
        Mockito.when(customersRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(customersRepository.save(Mockito.any(CustomersEntity.class))).thenReturn(new CustomersEntity());

        //Mocking a save of a customer
        CustomerRequestDTO checkCustomer= new CustomerRequestDTO("Gabriel", "lourii@gmail.com", "1234", "1234");
        customersService.save(checkCustomer);
        Mockito.verify(customersRepository).save(Mockito.any(CustomersEntity.class));


        //Testing the case where the password and confirm password do not match
        assertThrows(ForbiddenExeption.class, () -> customersService.save(new CustomerRequestDTO("Gabriel", "lourii@gmail.com", "1234", "12345")));

        //Testing the case where an email already exists in the database
        assertThrows(ConflictExeption.class, () -> {
            Mockito.when(customersRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
            customersService.save(new CustomerRequestDTO("Gabriel", "lourii@gmail.com", "1234", "1234"));
        });
    }

    @Test
    public void testUpdateCustomer() {
        //Setting up the environment for the update test

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encoded");
        Mockito.when(customersRepository.save(Mockito.any(CustomersEntity.class))).thenReturn(new CustomersEntity());

        //Building a customer for update test
        CustomersEntity existing= CustomersEntity.builder()
                .id(1L)
                .customerName("Gabriel")
                .email("lourii@gmail.com")
                .password("1234")
                .build();

        Mockito.when(customersRepository.findById(1L)).thenReturn(java.util.Optional.of(existing));

        customersService.update(1L, new CustomerRequestDTO("Lourii", "lourii@gmail.com", "1234", "1234"));

        assertEquals("Lourii", existing.getCustomerName());




    }

    @Test
    public void testDeleteCustomer() {
        //Building a customer for delete test
        CustomersEntity customerForDelete= CustomersEntity.builder()
                .id(1L)
                .customerName("Gabriel")
                .email("lourii@gmail.com")
                .password("1234")
                .build();
        Mockito.when(customersRepository.findById(1L)).thenReturn(java.util.Optional.of(customerForDelete));
        customersService.delete(1L);
        Mockito.verify(customersRepository).deleteById(customerForDelete.getId());

        assertThrows(NotFoundExeption.class,() -> customersService.delete(2L));





    }
}