package com.amar.lld.paymentgateway.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class IciciCreditCardHandler implements IPaymentHandlerService{

    IciciBankServiceClient client;
    ObjectMapper mapper;
    public IciciCreditCardHandler(IciciBankServiceClient client, ObjectMapper mapper){
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public PaymentResponse Payment(PaymentGatewayRequest request) {
        IciciCreditCardPaymentRequest paymentRequest = mapper.convertValue(request.paymentDetails(), IciciCreditCardPaymentRequest.class);
        System.out.println("Credit card payment has been successful");
        client.HandleCreditCardPayment(paymentRequest);
        String transactionId = String.format("trx_{}_success_{}", Bank.ICICI, LocalDate.now().toString());
        return new PaymentResponse(transactionId,Bank.ICICI, PaymentStatus.COMPLETED, LocalDateTime.now());
    }

    @Override
    public Bank getBank() {
        return Bank.ICICI;
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CREDIT_CARD;
    }
    
}
