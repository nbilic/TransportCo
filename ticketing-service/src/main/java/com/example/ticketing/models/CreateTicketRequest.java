package com.example.ticketing.models;

import java.time.Instant;

public record CreateTicketRequest(float price, Instant validFrom, Instant validUntil) {
}
