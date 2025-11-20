package com.amar.lld.paymentgateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amar.lld.paymentgateway.models.IPaymentRequest;
import com.amar.lld.paymentgateway.models.Merchant;
import com.amar.lld.paymentgateway.models.PaymentResponse;
import com.amar.lld.paymentgateway.service.IPaymentGatewayService;


@RestController
@RequestMapping("/api/payment")
public class PaymentGatewayController{

    private final IPaymentGatewayService _paymentGateway;

    public PaymentGatewayController(IPaymentGatewayService gatewayService){
        this._paymentGateway = gatewayService;
    }

    @PostMapping("/register")
    public boolean Register(@RequestBody Merchant merchant){
        return _paymentGateway.Register(merchant);
    }

    @PostMapping("/execute")
    public PaymentResponse ExecutePayment(@RequestBody IPaymentRequest request){
        return _paymentGateway.ExecutePayment(request);
    }
}