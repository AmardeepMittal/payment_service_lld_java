package com.amar.lld.paymentgateway.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.amar.lld.paymentgateway.models.Merchant;
import com.amar.lld.paymentgateway.models.PaymentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class PaymentGatewayDemo implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080/api/payment";

    public PaymentGatewayDemo(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void run(String... args) throws Exception {
        // Give server time to start
        Thread.sleep(2000);
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PAYMENT GATEWAY DEMO - REST API Client");
        System.out.println("Making HTTP calls to PaymentGatewayController");
        System.out.println("=".repeat(80) + "\n");

        // Step 1: Register a merchant
        registerMerchant();
        
        // Step 2: Make 20 payment requests
        for (int i = 1; i <= 20; i++) {
            System.out.println("Request #" + i + ":");
            executePayment(i);
            System.out.println();
        }

        System.out.println("=".repeat(80));
        System.out.println("DEMO COMPLETE");
        System.out.println("Check logs above to see load balancing between HDFC and ICICI");
        System.out.println("=".repeat(80) + "\n");
    }

    private void registerMerchant() {
        try {
            System.out.println("Registering Merchant...");
            Merchant merchant = new Merchant("MERCH001", "Test Merchant", "merchant@test.com", "E-commerce");
            
            String url = BASE_URL + "/register";
            Boolean response = restTemplate.postForObject(url, merchant, Boolean.class);
            
            System.out.println("Merchant Registration: " + (response ? "SUCCESS" : "FAILED"));
            System.out.println();
        } catch (Exception e) {
            System.err.println("Failed to register merchant: " + e.getMessage());
        }
    }

    private void executePayment(int requestNumber) {
        try {
            int amount = 1000 + (requestNumber * 100);
            
            // Create payment request - Jackson will use the "type" field to deserialize
            // HdfcCreditCardPaymentRequest request = new Json(
            //     amount,
            //     PaymentType.CREDIT_CARD,
            //     "4111111111111111",
            //     "Customer " + requestNumber,
            //     "12",
            //     "2025",
            //     "123"
            // );

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("amount", 1000);
            jsonObject.put("paymentType", "CREDIT_CARD");
            jsonObject.put("cardNumber", "4111111111111111");
            jsonObject.put("cardHolderName", "Customer " + requestNumber);
            jsonObject.put("expiryMonth", "12");
            jsonObject.put("expiryYear", "2025");
            jsonObject.put("cvv", "123");   
            
            String url = BASE_URL + "/execute";
            PaymentResponse response = restTemplate.postForObject(url, jsonObject, PaymentResponse.class);
            
            if (response != null) {
                System.out.println("  - Amount: $" + amount);
                System.out.println("  - Status: " + response.status());
                System.out.println("  - Transaction ID: " + response.TransactionId());
                System.out.println("  - Bank: " + response.bank());
            }
        } catch (Exception e) {
            System.err.println("  - Payment failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
