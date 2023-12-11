package com.hamdi.banking.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Role extends AbstractEntity {


    private String name;


    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
