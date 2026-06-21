package com.gabriel.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "OrderItems")
public class OrderItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "Quantity", nullable = false)
        private Integer quantity;
        @Column(name = "Price", nullable = false)
        private BigDecimal price;


        @ManyToOne
        @JoinColumn(name = "order_id")
        private OrderEntity order;
        @ManyToOne
        @JoinColumn(name = "product_id")
        private ProductsEntity product;

}

