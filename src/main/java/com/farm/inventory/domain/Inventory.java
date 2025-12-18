package com.farm.inventory.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Reference to Product (NOT a JPA relation intentionally)
     */
    @Column(nullable = false, unique = true)
    private Long productId;

    /**
     * Available stock quantity
     */
    @Column(nullable = false)
    private Integer availableQuantity;
}
