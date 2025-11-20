package com.amar.lld.paymentgateway.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amar.lld.paymentgateway.models.Bank;
import com.amar.lld.paymentgateway.models.BankConfig;
import com.amar.lld.paymentgateway.models.PaymentType;
import com.amar.lld.paymentgateway.router.BankRouter;
import com.amar.lld.paymentgateway.router.IBankRouter;
import com.amar.lld.paymentgateway.service.IBankService;
import com.amar.lld.paymentgateway.service.IPaymentHandlerService;

@Configuration
public class PaymentGatewayConfig {

    private final List<IPaymentHandlerService> allHandlers;

    /**
     * Spring automatically injects ALL beans implementing IPaymentHandlerService
     * This includes: HdfcCreditCardHandler, HdfcNetBankingHandler, 
     *                IciciCreditCardHandler, IciciNetBankingHandler, etc.
     */
    public PaymentGatewayConfig(List<IPaymentHandlerService> allHandlers) {
        this.allHandlers = allHandlers;
    }

    @Bean
    public Map<PaymentType, IPaymentHandlerService> hdfcPaymentHandlerMap() {
        return getPaymentHandlerMap(Bank.HDFC);
    }

    @Bean
    public Map<PaymentType, IPaymentHandlerService> iciciPaymentHandlerMap() {
        return getPaymentHandlerMap(Bank.ICICI);
    }

    @Bean
    public Map<Bank, IBankService> bankServiceMap(List<IBankService> bankServices) {    
        Map<Bank, IBankService> bankToServiceMap = new HashMap<>();
        for (IBankService bankService : bankServices) {
            bankToServiceMap.put(bankService.getBank(), bankService);
        }
        return bankToServiceMap;
    }

    @Bean
    public List<BankConfig> bankConfigs() {
        List<BankConfig> configs = new ArrayList<>();
        
        // HDFC Bank Configuration - 60% weight
        BankConfig hdfcConfig = new BankConfig(
            Bank.HDFC,
            "HDFC Bank",
            Arrays.asList(PaymentType.CREDIT_CARD, PaymentType.NET_BANKING),
            60  // 60% load/weight
        );
        
        // ICICI Bank Configuration - 40% weight
        BankConfig iciciConfig = new BankConfig(
            Bank.ICICI,
            "ICICI Bank",
            Arrays.asList(PaymentType.CREDIT_CARD, PaymentType.NET_BANKING),
            40  // 40% load/weight
        );
        
        configs.add(hdfcConfig);
        configs.add(iciciConfig);
        
        return configs;
    }

    @Bean
    public IBankRouter bankRouter(Map<Bank, IBankService> bankServiceMap, List<BankConfig> bankConfigs) {
        return new BankRouter(bankServiceMap, bankConfigs);
    }
    

    private Map<PaymentType, IPaymentHandlerService> getPaymentHandlerMap(Bank bank){
        Map<PaymentType, IPaymentHandlerService> handlerMap = new HashMap<>();
        
        // Filter and add ICICI handlers
        for (IPaymentHandlerService handler : allHandlers) {
            if (handler.getBank() == bank) {
                handlerMap.put(handler.getPaymentType(), handler);
            }
        }
        
        return handlerMap;
    }
}





