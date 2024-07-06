package org.example.paymetest;

import io.github.nazarovctrl.paymemerchantapi.dto.db.Order;
import io.github.nazarovctrl.paymemerchantapi.dto.db.Transaction;
import io.github.nazarovctrl.paymemerchantapi.repository.IMerchantRepository;

import org.example.paymetest.entity.OrderEntity;
import org.example.paymetest.entity.OrderTransactionEntity;
import org.example.paymetest.repository.OrderRepository;
import org.example.paymetest.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MerchantRepository implements IMerchantRepository {
    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;

    public MerchantRepository(OrderRepository orderRepository, TransactionRepository transactionRepository) {
        this.orderRepository = orderRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Order getOrderById(Long orderId) {
        OrderEntity entity = orderRepository.findById(orderId).orElse(null);
        if (entity == null) {
            return null;
        }
        return mapOrder(entity);
    }

    @Override
    public Transaction getTransactionByOrderId(Long orderId) {
        OrderTransactionEntity transaction = transactionRepository.findByOrder_Id(orderId).orElse(null);
        if (transaction == null) {
            return null;
        }
        return mapTransaction(transaction);
    }

    @Override
    public Transaction getTransactionByPaymeId(String id) {
        // id = Transaction ID generated in Payme backend
        // return transaction whose paymeId is equal to the id in the method param
        OrderTransactionEntity transaction = transactionRepository.findByPaycomId(id).orElse(null);
        if (transaction == null) {
            return null;
        }
        return mapTransaction(transaction);
    }

    @Override
    public String saveTransaction(Transaction tr) {
        OrderEntity order = orderRepository.findById(tr.getOrder().getOrderId()).orElse(null);
        OrderTransactionEntity transaction = OrderTransactionEntity.builder()
                .paycomId(tr.getPaymeId())
                .paycomTime(tr.getPaymeTime())
                .createTime(tr.getCreateTime())
                .performTime(tr.getPerformTime())
                .cancelTime(tr.getCancelTime())
                .reason(tr.getReason())
                .state(tr.getState())
                .order(order).build();

        if (tr.getId() != null) {
            transaction.setId(Long.valueOf(tr.getId()));
        }

        transactionRepository.save(transaction);

        return String.valueOf(transaction.getId());
    }

    @Override
    public void changeIsActive(Long orderId, Boolean isActive) {
        OrderEntity entity = orderRepository.findById(orderId).orElseThrow();
        entity.setIsActive(true);
        orderRepository.save(entity);
    }

    @Override
    public List<Transaction> getAllTransactionsByPaymeTimeBetween(long from, long to) {
        List<OrderTransactionEntity> entityList = transactionRepository.findByPaycomTimeBetween(from, to).orElse(null);
        if (entityList == null) {
            return null;
        }

        return entityList.stream().map(this::mapTransaction).toList();
    }


    public Order mapOrder(OrderEntity entity) {
        return new Order(entity.getId(), entity.getIsActive(), entity.getAmount());
    }

    private Transaction mapTransaction(OrderTransactionEntity transaction) {
        return new Transaction(transaction.getId().toString(), transaction.getPaycomId(), transaction.getPaycomTime(), transaction.getCreateTime(), transaction.getPerformTime(),
                transaction.getCancelTime(), transaction.getReason(), transaction.getState(), mapOrder(transaction.getOrder()));
    }
}