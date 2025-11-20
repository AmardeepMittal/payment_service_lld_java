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

#### 2. **Weighted Random**
- Randomly selects banks based on weight probability
- Good for varying traffic patterns
- Provides natural load balancing

#### 3. **Least Connections**
- Routes to bank with fewest active connections
- Accounts for bank processing capacity
- Optimal for real-time load balancing

#### 4. **Health-Based Distribution**  
- Routes based on bank health metrics
- Automatically avoids underperforming banks
- Adaptive to real-time conditions

### 📊 Traffic Analytics & Monitoring

- **Real-time Distribution Tracking**: Monitor how traffic is distributed across banks
- **Performance Analytics**: Track success rates, processing times, and throughput
- **Intelligent Recommendations**: Get optimal weight suggestions based on performance
- **Bank Performance Metrics**: Detailed analytics for each banking partner

### 💳 Payment Method Support

#### Credit/Debit Cards
```csharp
var request = PaymentRequestFactory.CreateCreditCardPayment(
    "CLIENT_001", 1500.50m, "4111111111111111", "12/25", "123", "John Doe");
```

#### UPI Payments
```csharp  
var request = PaymentRequestFactory.CreateUPIPayment(
    "CLIENT_002", 750.75m, "user@paytm");
```

#### Net Banking
```csharp
var request = PaymentRequestFactory.CreateNetBankingPayment(
    "CLIENT_001", 2000.00m, "username", "password");
```

## Architecture

### Core Components

1. **PaymentGateway**: Main orchestration service
2. **BankRouter**: Intelligent traffic distribution engine  
3. **BankAdapters**: Bank-specific payment processors
4. **TrafficAnalyzer**: Performance monitoring and analytics
5. **PaymentHandlers**: Payment method specific processors

### Traffic Distribution Flow

```
Payment Request → PaymentGateway → BankRouter → BankAdapter → PaymentHandler → Bank Response
                       ↓
                 Traffic Analytics ← Performance Metrics
```

## Configuration Example

```csharp

interface IBankAdapter {
    IPaymentResponse HandlePayment(IPaymentRequest request);
}

interface IPaymentHandler{
    IPaymentResponse Handle(IPaymentRequest request);
}

public class HdfcCreditCardPaymentHandler : IPaymentHandler{
    CreditCardPaymentResponse Handle(CreditCardPaymentRequest request){

    }
}

public class HdfNetbankingPaymentHandler : IPaymentHandler{
    NetbankingPaymentResponse Handle(NetbankingPaymentRequest request){

    }
}


public class HdfcBankAdapter : IBankAdapter{
    Dictionary<PaymentType, IPaymentHandler> handlerMap = new Dictionary<PaymentType, IPaymentHandler>();
    public HdfcBankAdapter(){
        handleMap = new Dictionary<PaymentType, IPaymentHandler>(){
            {PaymentType.CreditCard, new HdfcCreditCardPaymentHandler()},
            {PaymentType.Netbanking, new HdfNetbankingPaymentHandler()}
        };
    }
    public IPaymentResponse HandlePayment(IPaymentRequest request){
        var handler = handlerMap[request.Type];
        return handler.Handle(reqest);
    }
}

public interface IBankRouter{
    public IBankAdapter Route(IPaymentRequest request);
}

public class BankConfig{
    public string BankName {get; set;}
    public string Weight {get; set;}
}

public class BankRouter{
    var configMap = new Dictionary<string, int>();
    public BankRouter(List<BankConfig> configs){
        foreach(var config in configs){
            configMap[config.BankNam] = config.Weight;
        }
    }
    int requestCount = 0;

    public IBankAdapter Route(IPaymentRequest request){
        router++;
    }
}


```