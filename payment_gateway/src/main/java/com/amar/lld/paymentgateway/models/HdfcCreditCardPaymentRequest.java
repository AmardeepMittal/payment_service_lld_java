package com.amar.lld.paymentgateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HdfcCreditCardPaymentRequest implements IPaymentRequest {
    private int amount;
    private PaymentType paymentType;
    private String cardNumber;
    private String cardHolderName;   
    private String expiryMonth; 
    private String expiryYear; 
    private String cvv;
}