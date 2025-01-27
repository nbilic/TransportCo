package com.example.ticketing.payment;

import com.example.paymentservice.models.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {

    public static final String TOPIC = "paymentRequestTopic";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(int ticketId, float price) {
        try {
            var message = objectMapper.writeValueAsString(toPaymentRequest(ticketId, price));
            kafkaTemplate.send(TOPIC, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private PaymentRequest toPaymentRequest(int ticketId, float price) {
        return new PaymentRequest(ticketId, price);
    }
}
