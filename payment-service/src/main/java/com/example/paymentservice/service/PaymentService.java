package com.example.paymentservice.service;

import com.example.paymentservice.kafka.PaymentOutcomeProducer;
import com.example.paymentservice.models.PaymentEntity;
import com.example.paymentservice.models.Payment;
import com.example.paymentservice.models.PaymentStatus;
import com.example.paymentservice.models.PaymentRequest;
import com.example.paymentservice.paymentprovider.PaymentProvider;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.paymentservice.mappers.PaymentMapper.toPayment;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentProvider paymentProvider;
    private final PaymentOutcomeProducer paymentOutcomeProducer;
    private final PaymentRepository repository;

    public void charge(PaymentRequest request) {
        var outcome = paymentProvider.charge();
        var paymentStatus = outcome ? PaymentStatus.SUCCESS : PaymentStatus.FAILURE;

        repository.save(toPayment(request, paymentStatus));
        paymentOutcomeProducer.sendMessage(new Payment(request.ticketId(), paymentStatus));
    }
}