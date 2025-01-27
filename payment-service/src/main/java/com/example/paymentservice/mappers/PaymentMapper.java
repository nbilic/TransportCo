package com.example.paymentservice.mappers;

import com.example.paymentservice.models.PaymentEntity;
import com.example.paymentservice.models.PaymentRequest;
import com.example.paymentservice.models.PaymentStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class PaymentMapper {

    public static PaymentEntity toPayment(PaymentRequest request, PaymentStatus status) {
        return new PaymentEntity(request.ticketId(), request.price(), status);
    }
}
