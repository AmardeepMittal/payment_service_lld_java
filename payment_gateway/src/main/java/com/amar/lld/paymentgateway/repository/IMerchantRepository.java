package com.amar.lld.paymentgateway.repository;

import com.amar.lld.paymentgateway.models.Merchant;

public interface IMerchantRepository {
    String addMerchant(Merchant merchant);
    Merchant getMerchantById(String merchantKey);
    boolean isExists(String merchantKey);
}