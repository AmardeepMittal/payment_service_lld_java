package com.amar.lld.paymentgateway.service;

import com.amar.lld.paymentgateway.models.*;

public interface  IBankService {
    PaymentResponse ExecutePayment(PaymentGatewayRequest request);
    Bank getBank(); // Each service knows which bank it represents
}
