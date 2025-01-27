package com.example.paymentservice.kafka;

import com.example.paymentservice.models.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentOutcomeProducer {

    public static final String TOPIC = "paymentOutcomeTopic";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(Payment paymentOutcome) {
        sleep();
        try {
            var message = objectMapper.writeValueAsString(paymentOutcome);
            kafkaTemplate.send(TOPIC, message);
        } catch (Exception e) {
            // handle exception
        }
    }

    // simulate real time latency to test validation
    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
