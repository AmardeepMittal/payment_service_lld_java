package com.amar.lld.paymentgateway.models;

public record PaymentGatewayRequest(
    String merchantKey, 
    PaymentType paymentType, 
    Object paymentDetails) {
}

