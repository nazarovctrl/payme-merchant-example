package org.example.paymetest.repository;

import org.example.paymetest.entity.OrderTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<OrderTransactionEntity, Long> {
    Optional<OrderTransactionEntity> findByPaycomId(String id);

    Optional<List<OrderTransactionEntity>> findByPaycomTimeBetween(long from, long to);

    Optional<OrderTransactionEntity> findByOrder_Id(long id);
}