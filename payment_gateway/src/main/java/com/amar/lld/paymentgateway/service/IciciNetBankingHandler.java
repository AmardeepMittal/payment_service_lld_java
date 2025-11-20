package com.amar.lld.paymentgateway.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class IciciNetBankingHandler implements IPaymentHandlerService {

    IciciBankServiceClient client;
    ObjectMapper mapper;
    public IciciNetBankingHandler(IciciBankServiceClient client, ObjectMapper mapper){
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public PaymentResponse Payment(PaymentGatewayRequest request) {
        IciciNetBankingPaymentRequest paymentRequest = mapper.convertValue(request.paymentDetails(), IciciNetBankingPaymentRequest.class);
        System.out.println("ICICI net banking has been successful");
        client.HandleNetBankingPayment(paymentRequest);
        String transactionId = String.format("trx_{}_success_{}", Bank.ICICI, LocalDate.now().toString());
        return new PaymentResponse(transactionId,Bank.ICICI, PaymentStatus.COMPLETED, LocalDateTime.now());
    }

    @Override
    public Bank getBank() {
        return Bank.ICICI;
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.NET_BANKING;
    }
    
}
