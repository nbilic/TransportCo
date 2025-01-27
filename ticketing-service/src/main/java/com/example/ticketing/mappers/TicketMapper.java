package com.example.ticketing.mappers;

import com.example.paymentservice.models.PaymentStatus;
import com.example.ticketing.models.CreateTicketRequest;
import com.example.ticketing.models.Ticket;
import com.example.ticketing.models.TicketEntity;
import com.example.ticketing.models.TicketStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;

@NoArgsConstructor(access = AccessLevel.NONE)
public class TicketMapper {

    public static TicketEntity createTicket(CreateTicketRequest request) {
        var ticket = new TicketEntity();
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setValidFrom(request.validFrom());
        ticket.setValidUntil(request.validUntil());

        return ticket;
    }

    public static Ticket toTicket(TicketEntity entity) {
        return new Ticket(entity.getId(), entity.getStatus(), entity.getValidFrom(), entity.getValidUntil());
    }

    public static TicketStatus toTicketStatus(PaymentStatus paymentStatus) {
        return switch (paymentStatus) {
            case FAILURE -> TicketStatus.REJECTED;
            case SUCCESS -> TicketStatus.SUCCESS;
        };
    }
}
