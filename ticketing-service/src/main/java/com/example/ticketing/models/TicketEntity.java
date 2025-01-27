package com.example.ticketing.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Table(name = "Ticket")
@NoArgsConstructor
@Data
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private TicketStatus status;
    private Instant validFrom;
    private Instant validUntil;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public TicketEntity(TicketStatus status, Instant validFrom, Instant validUntil) {
       this.status = status;
       this.validUntil = validUntil;
       this.validFrom = validFrom;
    }

}
