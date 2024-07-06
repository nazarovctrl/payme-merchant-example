package org.example.paymetest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "custom_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long amount;

    @Column
    private Boolean isActive;

    public OrderEntity(Long id, Long amount, Boolean isActive) {
        this.id = id;
        this.amount = amount;
        this.isActive = isActive;
    }

    public OrderEntity(Long id) {
        this.id = id;
    }
}