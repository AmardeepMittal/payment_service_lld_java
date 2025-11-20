package com.amar.lld.paymentgateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IciciNetBankingPaymentRequest implements IPaymentRequest{
    private int amount;
    private PaymentType paymentType;
    public String ifscCode;
    public String accountNumber;   
    public String bankAddress;
    public String accountHolderName;
    public String merchantId;
}