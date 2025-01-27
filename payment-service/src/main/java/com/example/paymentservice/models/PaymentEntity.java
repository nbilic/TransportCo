package com.example.paymentservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int ticketId;
    private float price;
    private PaymentStatus status;

    public PaymentEntity(int ticketId, float price, PaymentStatus outcome) {
        this.status = outcome;
        this.price = price;
        this.ticketId = ticketId;
    }
}
