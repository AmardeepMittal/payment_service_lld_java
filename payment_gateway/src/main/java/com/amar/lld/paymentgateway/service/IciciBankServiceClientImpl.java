package com.amar.lld.paymentgateway.service;

import org.springframework.stereotype.Service;

import com.amar.lld.paymentgateway.models.IciciCreditCardPaymentRequest;
import com.amar.lld.paymentgateway.models.IciciNetBankingPaymentRequest;

@Service
public class IciciBankServiceClientImpl implements IciciBankServiceClient {
    
    @Override
    public boolean HandleCreditCardPayment(IciciCreditCardPaymentRequest request) {
        // Simulate ICICI bank API call for credit card payment
        System.out.println("  [ICICI API] Processing credit card payment for amount: " + request.amount);
        return true;
    }

    @Override
    public boolean HandleNetBankingPayment(IciciNetBankingPaymentRequest request) {
        // Simulate ICICI bank API call for net banking payment
        System.out.println("  [ICICI API] Processing net banking payment for amount: " + request.getAmount());
        return true;
    }
}
