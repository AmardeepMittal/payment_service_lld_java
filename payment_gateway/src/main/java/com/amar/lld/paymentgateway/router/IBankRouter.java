package com.amar.lld.paymentgateway.router;

import com.amar.lld.paymentgateway.models.Bank;
import com.amar.lld.paymentgateway.models.PaymentType;

public interface IBankRouter {
    Bank getNextBank(PaymentType paymentType);
}
