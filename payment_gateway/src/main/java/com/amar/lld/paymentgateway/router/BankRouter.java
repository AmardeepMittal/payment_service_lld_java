package com.amar.lld.paymentgateway.router;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amar.lld.paymentgateway.models.Bank;
import com.amar.lld.paymentgateway.models.BankConfig;
import com.amar.lld.paymentgateway.models.PaymentType;
import com.amar.lld.paymentgateway.service.IBankService;

public class BankRouter implements IBankRouter {

    Map<Bank, IBankService> bankServiceMap;
    List<BankConfig> bankConfigs;
    Map<Bank, Integer> bankLoadMap = new HashMap<>();

    public BankRouter(Map<Bank, IBankService> bankServiceMap, List<BankConfig> bankConfigs) {
        this.bankServiceMap = bankServiceMap;
        this.bankConfigs = bankConfigs;
        for (BankConfig config : bankConfigs) {
            bankLoadMap.put(config.getBank(), config.getLoad());
        }
    }

    @Override
    public Bank getNextBank(PaymentType paymentType) {
        int totalLoad = bankConfigs
                .stream()
                .filter(config -> config.getSupportedPaymentTypes().contains(paymentType))
                .mapToInt(BankConfig::getLoad)
                .sum();

        List<BankConfig> eligibleBanks = bankConfigs
                .stream()
                .filter(config -> config.getSupportedPaymentTypes().contains(paymentType))
                .collect(Collectors.toList());
        
        Bank selectedBank = null;

        //Smooth Weighted Round Robin Logic
        for(int i = 0; i < eligibleBanks.size(); i++){
            var currBankConfig = eligibleBanks.get(i);
            int currentLoad = currBankConfig.getLoad() + bankLoadMap.get(currBankConfig.getBank());
            bankLoadMap.put(currBankConfig.getBank(), currentLoad);
            if(selectedBank == null || bankLoadMap.get(currBankConfig.getBank()) > bankLoadMap.get(selectedBank)){
                selectedBank = currBankConfig.getBank();
            }
        }

        bankLoadMap.put(selectedBank, bankLoadMap.get(selectedBank) - totalLoad);
        return selectedBank;
    }
}