package com.gabriel.service;

import com.gabriel.Entity.CustomersEntity;
import com.gabriel.Entity.OrderEntity;
import com.gabriel.Entity.OrderItem;
import com.gabriel.Entity.ProductsEntity;
import com.gabriel.dto.OrderRequestDTO;
import com.gabriel.dto.OrderItemRequestDTO;
import com.gabriel.exception.NotFoundExeption;
import com.gabriel.repository.CustomersRepository;
import com.gabriel.repository.OrderRepository;
import com.gabriel.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomersRepository customersRepository;
    private final ProductsRepository productsRepository;
    private final OrderRepository orderRepository;


    @Transactional
    public void createOrder(OrderRequestDTO orders, Long id) {
        CustomersEntity customer= customersRepository.findById(id).orElseThrow(() -> new NotFoundExeption("Customer "+id +" not found"));

        OrderEntity order=new OrderEntity();
        order.setCustumer(customer);
        List<OrderItem> items=new ArrayList<>();
        BigDecimal totalPrice=BigDecimal.ZERO;



        for(OrderItemRequestDTO itemDTO:orders.orderItems()){
            ProductsEntity product= productsRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new NotFoundExeption("Product"+ itemDTO +" not found"));

            OrderItem orderItem=new OrderItem();

            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());
            orderItem.setPrice(product.getPrice());

            orderItem.setOrder(order);
            items.add(orderItem);

            BigDecimal subtotal=product.getPrice().multiply(BigDecimal.valueOf(itemDTO.quantity()));
            orderItem.setPrice(subtotal);
            totalPrice=totalPrice.add(subtotal);
        }
        order.setOrderItems(items);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);




    }
}
