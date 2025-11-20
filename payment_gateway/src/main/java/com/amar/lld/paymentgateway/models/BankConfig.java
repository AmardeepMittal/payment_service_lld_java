package com.amar.lld.paymentgateway.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BankConfig{
    Bank bank;
    String bankName;
    List<PaymentType> supportedPaymentTypes;
    int load; // For load balancing among banks
}
