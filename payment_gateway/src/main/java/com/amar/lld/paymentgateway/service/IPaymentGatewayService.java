package com.amar.lld.paymentgateway.service;

import com.amar.lld.paymentgateway.models.*;

public interface IPaymentGatewayService {
    String Register(Merchant merchant);
    PaymentResponse ExecutePayment(PaymentGatewayRequest request);
}
