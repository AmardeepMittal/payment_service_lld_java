package com.amar.lld.paymentgateway.service;

import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.IciciCreditCardPaymentRequest;
import com.amar.lld.paymentgateway.models.IciciNetBankingPaymentRequest;

@Component
public interface IciciBankServiceClient {
    boolean HandleCreditCardPayment(IciciCreditCardPaymentRequest request);
    boolean HandleNetBankingPayment(IciciNetBankingPaymentRequest request);
}
