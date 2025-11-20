package com.amar.lld.paymentgateway.utils;

import java.util.Base64;

public class Encoder {
    
    public static String encode(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
    
    public static String decode(String encodedInput) {
        if (encodedInput == null) {
            throw new IllegalArgumentException("Encoded input cannot be null");
        }
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
        return new String(decodedBytes);
    }
}
