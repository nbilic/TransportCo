package com.example.ticketvalidator.service;

import com.example.ticketing.models.TicketStatus;
import com.example.ticketvalidator.web.TicketingClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TicketValidationService {

    private final TicketingClient ticketingWebClient;

    public boolean validateTicket(int ticketId) {
        var ticket = ticketingWebClient.getTicket(ticketId);
        if (ticket.isEmpty()) {
            return false;
        }

        var isTicketActive = ticket.get().status() == TicketStatus.SUCCESS;
        var isValidTimePeriod = ticket.get().validFrom().isBefore(Instant.now()) && ticket.get().validUntil().isAfter(Instant.now());
        return isTicketActive && isValidTimePeriod;
    }
}
