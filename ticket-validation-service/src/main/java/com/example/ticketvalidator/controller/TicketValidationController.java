package com.example.ticketvalidator.controller;

import com.example.ticketvalidator.service.TicketValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/validation")
@RequiredArgsConstructor
public class TicketValidationController {

    private final TicketValidationService ticketValidationService;

    @Cacheable("ticketValidation")
    @GetMapping("/validate/{id}")
    public boolean validate(@PathVariable int id) {
        return ticketValidationService.validateTicket(id);
    }
}
