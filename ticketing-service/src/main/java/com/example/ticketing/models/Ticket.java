package com.example.ticketing.models;

import java.time.Instant;

public record Ticket(int id, TicketStatus status, Instant validFrom, Instant validUntil) {
}
