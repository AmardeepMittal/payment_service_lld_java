package com.amar.lld.paymentgateway.service;

import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.HdfcCreditCardPaymentRequest;
import com.amar.lld.paymentgateway.models.HdfcNetBankingPaymentRequest;

@Component
public interface  HdfcBankServiceClient {
    boolean HandleCreditCardPayment(HdfcCreditCardPaymentRequest request);
    boolean HandleNetBankingPayment(HdfcNetBankingPaymentRequest request);
}
