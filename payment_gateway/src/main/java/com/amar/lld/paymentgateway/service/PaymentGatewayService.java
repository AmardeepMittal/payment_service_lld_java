package com.amar.lld.paymentgateway.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.amar.lld.paymentgateway.models.*;
import com.amar.lld.paymentgateway.repository.IMerchantRepository;
import com.amar.lld.paymentgateway.router.IBankRouter;

@Component
public class PaymentGatewayService implements IPaymentGatewayService{
    Map<Bank, IBankService> bankServices;
    IMerchantRepository _repository;
    IBankRouter bankRouter;

    public PaymentGatewayService(Map<Bank, IBankService> bankServices,
                                 IMerchantRepository repository, 
                                 IBankRouter bankRouter){
        this.bankServices = bankServices;
        this._repository = repository;
        this.bankRouter = bankRouter;
    }
    
    @Override
    public boolean Register(Merchant merchant) {
        _repository.addMerchant(merchant);
        return true;
    }

    @Override
    public PaymentResponse ExecutePayment(IPaymentRequest request) {
       // Use BankRouter for load balancing
       Bank selectedBank = bankRouter.getNextBank(request.getPaymentType());
       System.out.println("Routing payment to: " + selectedBank);
       return bankServices.get(selectedBank).ExecutePayment(request);
    }
}