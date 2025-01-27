package com.example.paymentservice.paymentprovider;

import org.springframework.stereotype.Service;

@Service
public class StripeService implements PaymentProvider {

    public boolean charge(){
        return true; // simulate payment
    };
}
