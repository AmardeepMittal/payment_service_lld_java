package com.amar.lld.paymentgateway.service;

import org.springframework.stereotype.Service;

import com.amar.lld.paymentgateway.models.HdfcCreditCardPaymentRequest;
import com.amar.lld.paymentgateway.models.HdfcNetBankingPaymentRequest;

@Service
public class HdfcBankServiceClientImpl implements HdfcBankServiceClient {
    
    @Override
    public boolean HandleCreditCardPayment(HdfcCreditCardPaymentRequest request) {
        // Simulate HDFC bank API call for credit card payment
        System.out.println("  [HDFC API] Processing credit card payment for amount: " + request.getAmount());
        return true;
    }

    @Override
    public boolean HandleNetBankingPayment(HdfcNetBankingPaymentRequest request) {
        // Simulate HDFC bank API call for net banking payment
        System.out.println("  [HDFC API] Processing net banking payment for amount: " + request.getAmount());
        return true;
    }
}
