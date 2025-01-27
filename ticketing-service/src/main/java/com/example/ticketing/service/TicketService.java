package com.example.ticketing.service;

import com.example.paymentservice.models.Payment;
import com.example.ticketing.payment.PaymentProducer;
import com.example.ticketing.models.*;
import com.example.ticketing.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.example.ticketing.mappers.TicketMapper.*;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;
    private final PaymentProducer paymentProducer;

    public Ticket getTicket(int id) {
        var entity = requireTicket(id);
        return toTicket(entity);
    }

    public Ticket reserveTicket(CreateTicketRequest request) {
        var entity = save(createTicket(request));
        paymentProducer.sendMessage(entity.getId(), request.price());
        return toTicket(entity);
    }

    public void finalizeTicketReservation(Payment outcome) {
        var ticket = requireTicket(outcome.ticketId());
        ticket.setStatus(toTicketStatus(outcome.status()));
        save(ticket);
    }

    public void cancelTicket(int ticketId) {
        var ticket = requireTicket(ticketId);
        // validate if ticket can be cancelled
        ticket.setStatus(TicketStatus.CANCELED);
        // trigger payment refund process logic
        save(ticket);
    }

    private TicketEntity save(TicketEntity ticket) {
        return repository.save(ticket);
    }

    private TicketEntity requireTicket(int id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
