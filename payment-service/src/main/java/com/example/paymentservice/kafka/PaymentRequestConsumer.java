package com.example.paymentservice.kafka;

import com.example.paymentservice.models.PaymentRequest;
import com.example.paymentservice.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentRequestConsumer {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "paymentRequestTopic", groupId = "my-group-id")
    public void listen(String message) throws JsonProcessingException {
        paymentService.charge(toPaymentRequest(message));
    }

    private PaymentRequest toPaymentRequest(String message) throws JsonProcessingException {
        return objectMapper.readValue(message, PaymentRequest.class);
    }
}
