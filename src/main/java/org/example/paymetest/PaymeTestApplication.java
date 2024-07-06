package org.example.paymetest;

import org.example.paymetest.entity.OrderEntity;
import org.example.paymetest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymeTestApplication implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(PaymeTestApplication.class, args);
    }

    @Override
    public void run(String... args) {
        orderRepository.save(new OrderEntity(1L, 1000L, true));
    }
}
