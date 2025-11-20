package com.amar.lld.paymentgateway.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class HdfcCreditCardHandler implements IPaymentHandlerService{

    private final HdfcBankServiceClient hdfcClient;
    ObjectMapper mapper;
    public HdfcCreditCardHandler(HdfcBankServiceClient client, ObjectMapper mapper){
        hdfcClient = client;
        this.mapper = mapper;
    }
    @Override
    public PaymentResponse Payment(IPaymentRequest request) {
        HdfcCreditCardPaymentRequest paymentRequest = mapper.convertValue(request, HdfcCreditCardPaymentRequest.class);
        hdfcClient.HandleCreditCardPayment(paymentRequest);
        System.out.println("HdfcCreditCardHandler.Payment() has been processed successfully");
        String transactionId = String.format("trx_{}_success_{}", Bank.HDFC, LocalDate.now().toString());
        return new PaymentResponse(transactionId,Bank.HDFC, PaymentStatus.COMPLETED, LocalDateTime.now());
    }
    @Override
    public Bank getBank() {
       return Bank.HDFC;
    }
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CREDIT_CARD;
    }
}