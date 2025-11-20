package com.amar.lld.paymentgateway.models;

import java.time.LocalDateTime;

public record  PaymentResponse(String TransactionId, Bank bank, PaymentStatus status, LocalDateTime dateTime ) {

}
