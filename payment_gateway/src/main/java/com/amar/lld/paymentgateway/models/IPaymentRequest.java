package com.amar.lld.paymentgateway.models;

public interface IPaymentRequest {
    int getAmount();
    PaymentType getPaymentType();
}
