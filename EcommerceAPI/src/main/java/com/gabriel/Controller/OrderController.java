package com.gabriel.Controller;

import com.gabriel.dto.OrderRequestDTO;
import com.gabriel.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v3/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;



    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@PathVariable Long id,@Valid @RequestBody OrderRequestDTO orderItemDTO) {
        orderService.createOrder(orderItemDTO,id);
    }
}

