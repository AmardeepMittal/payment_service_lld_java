package com.amar.lld.paymentgateway.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IciciCreditCardPaymentRequest implements IPaymentRequest{
    public int amount;
    public PaymentType paymentType;
    public String cardNumber;
    public String cardHolderName;   
    public String expiryMonth; 
    public String expiryYear; 
    public String cvv;
}