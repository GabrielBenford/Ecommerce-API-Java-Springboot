package com.gabriel.Controller;

import com.gabriel.config.SecurityConfiguration;
import com.gabriel.dto.ProductRequestDTO;
import com.gabriel.dto.ProductsResponseDTO;
import com.gabriel.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/products")
@RequiredArgsConstructor
@SecurityRequirement(name= SecurityConfiguration.SECURITY)
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductsResponseDTO> findAllProducts(){
        return productService.findAllProducts();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void createProduct(@Valid @RequestBody ProductRequestDTO newProduct) {
        productService.createProduct(newProduct);
    }
}

