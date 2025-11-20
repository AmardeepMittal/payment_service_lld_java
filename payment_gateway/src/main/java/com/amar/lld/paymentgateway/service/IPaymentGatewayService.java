package com.amar.lld.paymentgateway.service;

import com.amar.lld.paymentgateway.models.*;

public interface IPaymentGatewayService {
    boolean Register(Merchant merchant);
    PaymentResponse ExecutePayment(IPaymentRequest request);
}
