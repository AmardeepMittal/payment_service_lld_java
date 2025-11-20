package com.amar.lld.paymentgateway.models;

public record Merchant(
    String merchantId,
    String merchantName,
    String merchantEmail,
    String category
) {
    // Compact constructor for validation
    public Merchant {        
        if(merchantName == null || merchantName.isEmpty()) {
            throw new IllegalArgumentException("Merchant Name cannot be null or empty");
        }
        if(merchantEmail == null || merchantEmail.isEmpty()) {
            throw new IllegalArgumentException("Merchant Email cannot be null or empty");
        }
        if(category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
    }
}