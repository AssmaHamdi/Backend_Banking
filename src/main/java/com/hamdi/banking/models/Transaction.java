package com.hamdi.banking.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name ="_transaction")
public class Transaction  extends AbstractEntity{



    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255) CHECK (type IN ('DEPOSIT', 'TRANSFERT'))")
    private TransactionType type;

    private String destinationIban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
