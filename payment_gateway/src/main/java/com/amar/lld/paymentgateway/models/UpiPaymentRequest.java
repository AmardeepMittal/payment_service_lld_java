package com.amar.lld.paymentgateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpiPaymentRequest implements IPaymentRequest {
    private String upiId;
    private String merchantId;
    private int amount;
    private PaymentType paymentType;
}
