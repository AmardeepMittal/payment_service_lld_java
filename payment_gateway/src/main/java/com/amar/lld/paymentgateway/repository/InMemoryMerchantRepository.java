package com.amar.lld.paymentgateway.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amar.lld.paymentgateway.models.Merchant;
import com.amar.lld.paymentgateway.utils.Encoder;

@Repository
public class InMemoryMerchantRepository implements IMerchantRepository{
    private final Map<String, Merchant> merchantMap;

    public InMemoryMerchantRepository() {
        this.merchantMap = new HashMap<>();
    }

    @Override
    public String addMerchant(Merchant merchant) {
        if(merchant == null) {
            throw new IllegalArgumentException("Merchant cannot be null");
        }
        if(merchantMap.containsKey(merchant.merchantId())) {
            throw new IllegalArgumentException("Merchant with ID " + merchant.merchantId() + " already exists");
        }
        String merchantKey = "MERCHANT_"+ merchant.merchantName().toUpperCase() + "_" + System.currentTimeMillis();
        String encodedMerchantKey = Encoder.encode(merchantKey);
        merchantMap.put(encodedMerchantKey, merchant);
        return encodedMerchantKey;
    }

    @Override
    public Merchant getMerchantById(String merchantKey) {
        return merchantMap.get(merchantKey);
    }

    @Override
    public boolean isExists(String merchantKey) {
        return merchantMap.containsKey(merchantKey);
    }

}