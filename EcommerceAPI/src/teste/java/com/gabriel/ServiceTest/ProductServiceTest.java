package com.gabriel.ServiceTest;

import com.gabriel.Entity.ProductsEntity;
import com.gabriel.dto.ProductRequestDTO;
import com.gabriel.dto.ProductsResponseDTO;
import com.gabriel.repository.ProductsRepository;
import com.gabriel.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    ProductsRepository productsRepository;

    @Test
    public void findAllProductsTest() {
        //Building a test product entity
        ProductsEntity productTest= ProductsEntity.builder()
                .id(1L)
                .name("Hammer")
                .price(new java.math.BigDecimal("19.99"))
                .stock(100)
                .build();
        Mockito.when(productsRepository.findAll()).thenReturn(java.util.Collections.singletonList(productTest));
        List<ProductsResponseDTO> products=productService.findAllProducts();
        System.out.println(products);

        //Checking if the products exists and have their data correctly
        assertNotNull(products);
        assertEquals("Hammer", products.get(0).name());
        assertEquals(new java.math.BigDecimal("19.99"), products.get(0).price());

    }

    @Test
    public void createProductTest() {
      //Setting the config for test
      Mockito.when(productsRepository.save(Mockito.any(ProductsEntity.class))).thenReturn(new ProductsEntity());

        //Creating a product request DTO to test the createProduct method
        ProductRequestDTO productRequestDTO = new ProductRequestDTO("Hammer", new java.math.BigDecimal("19.99"), 100);
        productService.createProduct(productRequestDTO);

        //Verifying that the save method was called once with any ProductsEntity object
        Mockito.verify(productsRepository, Mockito.times(1)).save(Mockito.any(ProductsEntity.class));
    }
}