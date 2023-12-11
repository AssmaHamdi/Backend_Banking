package com.hamdi.banking.dto;


import com.hamdi.banking.models.Transaction;
import com.hamdi.banking.models.TransactionType;
import com.hamdi.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private  Integer id;

    private BigDecimal amount;

    private TransactionType type;

    private String destinationIban;

    private Integer user_id;

    public static TransactionDto fromEntity(Transaction transaction) {

        if (transaction==null) {
            return  null;
        }
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .user_id(transaction.getUser().getId())
                .build();
    }
    public static Transaction toEntity(TransactionDto transaction) {

        if (transaction==null) {
            return  null;
        }
        return Transaction.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .user(
                        User.builder()
                                .id(transaction.getUser_id())
                                .build())
                .build();
    }
}
