package org.example.paymetest.entity;

import io.github.nazarovctrl.paymemerchantapi.enums.OrderCancelReason;
import io.github.nazarovctrl.paymemerchantapi.enums.TransactionState;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Builder
@Entity
@Table(name = "order_transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String paycomId;
    @Column
    private long paycomTime;
    @Column
    @Builder.Default
    private long createTime = new Date().getTime();
    @Column
    private long performTime;
    @Column
    private long cancelTime;
    @Column
    private OrderCancelReason reason;
    @Builder.Default
    @Column(nullable = false)
    private TransactionState state = TransactionState.STATE_IN_PROGRESS;
    @OneToOne
    private OrderEntity order;
}