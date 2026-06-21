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
@Table(name = "Products")
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Names", nullable = false)
    private String name;
    @Column(name = "Prices", nullable = false)
    private BigDecimal price;
    @Column(name = "Stock", nullable = false)
    private Integer stock;


}
