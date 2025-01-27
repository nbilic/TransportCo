package com.example.ticketing.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TicketStatus {

    PENDING(1),
    SUCCESS(2),
    REJECTED(3),
    CANCELED(4);

    private final int id;
}
