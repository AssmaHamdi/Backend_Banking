package com.hamdi.banking.dto;

import com.hamdi.banking.models.Contact;
import com.hamdi.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContactDto {

    private  Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String iban;

    private Integer user_id;

    public static ContactDto fromEntity(Contact contact){

        if (contact==null) {
            return  null;
        }
        return ContactDto.builder()
                .id(contact.getId())
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user_id(contact.getUser().getId())
                .build();

    }
    public static Contact toEntity(ContactDto contact){

        if (contact==null) {
            return  null;
        }
        return Contact.builder()
                .id(contact.getId())
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(
                        User.builder()
                        .id(contact.getUser_id())
                        .build())
                .build();

    }
}
