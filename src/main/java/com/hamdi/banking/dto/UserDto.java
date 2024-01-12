package com.hamdi.banking.dto;

import com.hamdi.banking.models.Role;
import com.hamdi.banking.models.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull(message = "firstname must not be null ")
    @NotEmpty(message = "firstname must not be empty ")
    @NotBlank(message = "firstname must not be blank ")
    private String firstname;

    @NotNull
    @NotEmpty
    @NotBlank
    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank


    @Email(message = "L'email n'est conforme")
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min =5, max=10, message = "le size de password doit etre entre 5 et 10")
    private String password;

    public static UserDto fromEntity(User user) {

        if(user==null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto user) {

        if(user==null) {
            return null;
        }
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public Role getRole() {
        return new Role();
    }
}
