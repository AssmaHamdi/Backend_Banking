package com.hamdi.banking.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
