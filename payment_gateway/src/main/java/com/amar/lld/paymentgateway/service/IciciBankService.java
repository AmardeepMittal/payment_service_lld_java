package com.amar.lld.paymentgateway.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.*;

@Component
public class IciciBankService implements IBankService {

    Map<PaymentType, IPaymentHandlerService> paymentHandlers;

    public IciciBankService(
        @Qualifier("iciciPaymentHandlerMap")
        Map<PaymentType, IPaymentHandlerService> handlers){
        paymentHandlers = handlers;
    }

    @Override
    public PaymentResponse ExecutePayment(IPaymentRequest request) {
        IPaymentHandlerService handler = paymentHandlers.get(request.getPaymentType());
        return handler.Payment(request);
    }

    @Override
    public Bank getBank() {
        return Bank.ICICI;
    }

}
