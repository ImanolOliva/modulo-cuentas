package com.example.cuentas.modulo.cuentas.dto;


import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {

    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private Integer age;

}
