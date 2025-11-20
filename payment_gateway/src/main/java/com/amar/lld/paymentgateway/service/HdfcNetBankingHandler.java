package com.amar.lld.paymentgateway.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HdfcNetBankingHandler implements IPaymentHandlerService {

    private final HdfcBankServiceClient hdfcClient;
    ObjectMapper mapper;
    public HdfcNetBankingHandler(HdfcBankServiceClient client, ObjectMapper mapper){
        hdfcClient = client;
        this.mapper = mapper;
    }

    @Override
    public PaymentResponse Payment(PaymentGatewayRequest request) {
        HdfcNetBankingPaymentRequest paymentRequest = mapper.convertValue(request.paymentDetails(), HdfcNetBankingPaymentRequest.class);
        System.out.println("Net banking payment is successful");
        
        hdfcClient.HandleNetBankingPayment(paymentRequest);
        String transactionId = String.format("trx_{}_success_{}", Bank.HDFC, LocalDate.now().toString());
        return new PaymentResponse(transactionId,Bank.HDFC, PaymentStatus.COMPLETED, LocalDateTime.now());
    }

    @Override
    public Bank getBank() {
        return Bank.HDFC;
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.NET_BANKING;
    }
    
}
