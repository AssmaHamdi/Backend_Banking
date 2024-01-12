package com.hamdi.banking.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
public class Contact extends AbstractEntity {


    private String firstname;

    private String lastname;

    private String email;

    private String iban;

    @ManyToOne
    @JoinColumn(name = "id-user")
    private User user;
}
