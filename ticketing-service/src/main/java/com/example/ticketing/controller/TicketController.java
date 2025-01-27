package com.example.ticketing.controller;

import com.example.ticketing.models.CreateTicketRequest;
import com.example.ticketing.models.Ticket;
import com.example.ticketing.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/ticketing")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/ticket")
    public Ticket reserveTicket(@RequestBody CreateTicketRequest request) {
        log.info("Reserving new ticket for request {}", request);
        return ticketService.reserveTicket(request);
    }

    @GetMapping("/ticket/{id}")
    public Ticket getTicket(@PathVariable int id) {
        log.info("Fetching ticket of id {}", id);
        return ticketService.getTicket(id);
    }

    @DeleteMapping("/ticket/{id}")
    public void deleteTicket(@PathVariable int id) {
        log.info("Deleting ticket of id {}", id);
        ticketService.cancelTicket(id);
    }
}
