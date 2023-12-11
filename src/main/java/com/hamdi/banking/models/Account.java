package com.hamdi.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Account extends AbstractEntity {


    private  String iban;

    @OneToOne
    @JoinColumn(name="id_user")
    private User user;

}
