package com.amar.lld.paymentgateway.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.*;

@Component
public class HdfcBankService implements IBankService {

    Map<PaymentType, IPaymentHandlerService> paymentHandlereMap;

    public HdfcBankService(
        @Qualifier("hdfcPaymentHandlerMap")
        Map<PaymentType, IPaymentHandlerService> paymentHandlereMap){
        this.paymentHandlereMap = paymentHandlereMap;
    }

    @Override
    public PaymentResponse ExecutePayment(PaymentGatewayRequest request) {
        IPaymentHandlerService paymentHandler = paymentHandlereMap.get(request.paymentType());
        
        if (paymentHandler == null) {
            throw new IllegalArgumentException("No handler found for payment type: " + request.paymentType());
        }
        return paymentHandler.Payment(request);
    }

    @Override
    public Bank getBank() {
        return Bank.HDFC;
    }

}
