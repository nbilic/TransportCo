package com.example.ticketing.payment;

import com.example.paymentservice.models.Payment;
import com.example.ticketing.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final TicketService service;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "paymentOutcomeTopic", groupId = "my-group-id")
    public void listen(String message) {
        try {
            var outcome = toPaymentOutcome(message);
            service.finalizeTicketReservation(outcome);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Payment toPaymentOutcome(String message) throws JsonProcessingException {
        return objectMapper.readValue(message, Payment.class);
    }
}
