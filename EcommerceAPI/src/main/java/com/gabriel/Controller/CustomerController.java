package com.gabriel.Controller;


import com.gabriel.config.SecurityConfiguration;
import com.gabriel.dto.CustomerRequestDTO;
import com.gabriel.dto.CustomerResponseDTO;
import com.gabriel.service.CustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "Endpoints for managing customers")
@SecurityRequirement(name= SecurityConfiguration.SECURITY)
public class CustomerController {

    private final CustomersService custumersService;

@GetMapping
@ResponseStatus(HttpStatus.OK)

@Operation(summary = "Get a list of all customers")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "401", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
    public List<CustomerResponseDTO> findAll() {
        return custumersService.findAll();
    }

@GetMapping("Page")
@ResponseStatus(HttpStatus.OK)

@Operation(summary = "Get a list of all customers by page")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", description = "Invalid page or customers parameter"),
        @ApiResponse(responseCode = "401", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
    public List<CustomerResponseDTO> findAll(@RequestParam("page") int page, @RequestParam("customers") int customers) {
        return custumersService.findAllByPage(page, customers);
    }

@GetMapping("/{id}")
@ResponseStatus(HttpStatus.OK)

@Operation(summary = "Get a customer by its id")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", description = "Invalid id"),
        @ApiResponse(responseCode = "401", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
    public CustomerResponseDTO findById(@PathVariable Long id) {
    return custumersService.findById(id);
    }


@PostMapping
@ResponseStatus(HttpStatus.CREATED)

@Operation(summary = "Create a new customer")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Customer created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized access"),
        @ApiResponse(responseCode = "409", description = "Some of the data already exists")
})
    public void saveNewCustomer(@RequestBody @Valid CustomerRequestDTO newCustumer){
        custumersService.save(newCustumer);
    }



@PutMapping("/{id}")
@ResponseStatus(HttpStatus.ACCEPTED)

@Operation(summary = "Update an existing customer")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid id"),
        @ApiResponse(responseCode = "401", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
    public void updateCustomer(@PathVariable Long id,@RequestBody CustomerRequestDTO custumer){
        custumersService.update(id,custumer);
    }



@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)

@Operation(summary = "Delete an existing customer")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid id"),
        @ApiResponse(responseCode = "401", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
  public void deleteCustomer(@PathVariable Long id){
        custumersService.delete(id);
    }

}

