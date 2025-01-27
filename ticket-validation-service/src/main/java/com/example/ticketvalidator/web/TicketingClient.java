package com.example.ticketvalidator.web;

import com.example.ticketing.models.Ticket;
import com.example.ticketvalidator.configuration.TicketingProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketingClient {

    private static final String GET_TICKET_PATH = "/v1/ticketing/ticket/";

    private final RestTemplate restTemplate;
    private final TicketingProperties properties;

    public Optional<Ticket> getTicket(int ticketId) {
        var url = properties.getBaseUrl() + GET_TICKET_PATH + ticketId;
        try {
            var ticket = restTemplate.getForObject(url, Ticket.class);
            return Optional.ofNullable(ticket);
        } catch (Exception e) {
            log.error("Error fetching ticket of id {}", ticketId);
            return Optional.empty();
        }
    }
}
