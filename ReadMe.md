# Payment Gateway with Intelligent Traffic Distribution

A comprehensive Payment Gateway system that demonstrates advanced traffic distribution algorithms for routing payments across multiple banks with different capacity weightings.

## Overview

This system implements a sophisticated payment processing platform that can intelligently distribute payment traffic across multiple banking partners based on configurable weights, performance metrics, and load balancing strategies.

## Key Features

### 🏦 Multi-Bank Support
- **HDFC Bank**: Supports Credit Cards, NetBanking, UPI
- **ICICI Bank**: Supports Credit Cards, Debit Cards, NetBanking, UPI  
- **SBI Bank**: Supports NetBanking, UPI

### 🔀 Advanced Traffic Distribution Strategies

#### 1. **Weighted Round Robin**
- Distributes traffic based on configured weights
- Ensures fair distribution over time
- Best for predictable traffic patterns

### 💳 Payment Method Support

#### Credit/Debit Cards
```csharp
var request = ICICICreditCardPayment(
    "CLIENT_001", 1500.50m, "4111111111111111", "12/25", "123", "John Doe");
```

#### UPI Payments
```csharp  
var request = ICICIUPIPayment(
    "CLIENT_002", 750.75m, "user@paytm");
```

#### Net Banking
```csharp
var request = ICICINetBankingPayment(
    "CLIENT_001", 2000.00m, "username", "password");
```

## Architecture

### Core Components

1. **PaymentGateway**: Main orchestration service
2. **BankRouter**: Intelligent traffic distribution engine  
3. **BankService**: Bank-specific payment processors
5. **PaymentHandlers**: Payment method specific processors

### Traffic Distribution Flow

```
Payment Request → PaymentGateway → BankRouter → BankService → PaymentHandler → Bank Response
                       
```

## Configuration Example

```java

public interface  IBankService {
    PaymentResponse ExecutePayment(IPaymentRequest request);
    Bank getBank(); // Each service knows which bank it represents
}

public interface  IPaymentHandlerService {
    Bank getBank();
    PaymentType getPaymentType();
    PaymentResponse Payment(IPaymentRequest request);
}


public class HdfcCreditCardPaymentHandler : IPaymentHandlerService{
    PaymentResponse Handle(HdfcCreditCardPaymentRequest request){

    }
}

public class HdfNetbankingPaymentHandler : IPaymentHandlerService{
    PaymentResponse Handle(HdfcNetbankingPaymentRequest request){

    }
}


public class HdfcBankService implements IBankService {

    Map<PaymentType, IPaymentHandlerService> paymentHandlereMap;

    public HdfcBankService(
        @Qualifier("hdfcPaymentHandlerMap")
        Map<PaymentType, IPaymentHandlerService> paymentHandlereMap){
        this.paymentHandlereMap = paymentHandlereMap;
    }

    @Override
    public PaymentResponse ExecutePayment(IPaymentRequest request) {
        IPaymentHandlerService paymentHandler = paymentHandlereMap.get(request.getPaymentType());
        return paymentHandler.Payment(request);
    }

    @Override
    public Bank getBank() {
        return Bank.HDFC;
    }

}


public interface IBankRouter{
    public Bank Route(IPaymentRequest request);
}

public class BankConfig{
    public Bank BankName {get; set;}
    public string Weight {get; set;}
}

public class BankRouter{
    var configMap = new Dictionary<string, int>();
    Map<Bank, IBankService> banks;
    public BankRouter(Map<Bank, IBankService> banks, List<BankConfig> configs){
        foreach(var config in configs){
            configMap[config.BankNam] = config.Weight;
        }
    }
    int requestCount = 0;

    public Bank Route(IPaymentRequest request){
        return Bank.HDFC;
    }
}
```