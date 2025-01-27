package com.example.ticketing.controller;

import com.example.ticketing.models.CreateTicketRequest;
import com.example.ticketing.models.Ticket;
import com.example.ticketing.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/ticketing")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/ticket")
    public Ticket createTicket(@RequestBody CreateTicketRequest request) {
        return ticketService.reserveTicket(request);
    }

    @GetMapping("/ticket/{id}")
    public Ticket getTicket(@PathVariable int id) {
        return ticketService.getTicket(id);
    }

    @DeleteMapping("/ticket/{id}")
    public void deleteTicket(@PathVariable int id) {
        ticketService.cancelTicket(id);
    }
}
