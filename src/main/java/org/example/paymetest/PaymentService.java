package org.example.paymetest;

import io.github.nazarovctrl.paymemerchantapi.service.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {
    @Override
    public void receive(String id) {
        // You should write logic, such as what to do after successful payment
        // For example give your product to user
    }

    @Override
    public boolean canRefund(String id) {
        // Method for checking a transaction for cancellation
        // For example, you sold a product to a user, but the user wants his money back.
        // You should check for a product that is eligible for a refund
        return true;
    }

    @Override
    public void refund(String id) {
        // You should write logic that will work after the user gets their money back
        // For example, you could write logic that removes a product from the user
        // or revokes permission to use a service for the user
    }
}