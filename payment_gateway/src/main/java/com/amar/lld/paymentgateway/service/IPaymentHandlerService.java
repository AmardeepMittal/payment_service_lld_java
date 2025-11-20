package com.amar.lld.paymentgateway.service;

import com.amar.lld.paymentgateway.models.*;

public interface  IPaymentHandlerService {
    Bank getBank();
    PaymentType getPaymentType();
    PaymentResponse Payment(PaymentGatewayRequest request);
}
