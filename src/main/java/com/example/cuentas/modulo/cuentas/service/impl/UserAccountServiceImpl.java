package com.example.cuentas.modulo.cuentas.service.impl;


import com.example.cuentas.modulo.cuentas.dto.UserAccountDTO;
import com.example.cuentas.modulo.cuentas.persistence.entity.repository.UserAccountRepository;
import com.example.cuentas.modulo.cuentas.persistence.entity.users.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAccountServiceImpl {

    @Autowired
    private UserAccountRepository userAccountRepository;



    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO) {
        try{
            this.validaciones(userAccountDTO,userAccountRepository);
            UserAccount user = new UserAccount();
            user.setUsername(userAccountDTO.getUsername());
            user.setPassword(userAccountDTO.getPassword());
            user.setEmail(userAccountDTO.getEmail());
            user.setAge(userAccountDTO.getAge());
            UserAccount savedUser = userAccountRepository.save(user);
            return toDTO(savedUser);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

    private UserAccountDTO toDTO(UserAccount user) {
        UserAccountDTO dto = new UserAccountDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        return dto;
    }

    private void validaciones(UserAccountDTO userAccountDTO,UserAccountRepository userAccountRepository){
            if (userAccountDTO.getUsername() == null || userAccountDTO.getUsername().isBlank()) {
                throw new IllegalArgumentException("El nombre de usuario es obligatorio");
            }

            if (userAccountDTO.getPassword() == null || userAccountDTO.getPassword().length() < 6) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
            }

            if (userAccountDTO.getEmail() == null || !userAccountDTO.getEmail().contains("@")) {
                throw new IllegalArgumentException("El email no es válido");
            }

            if (userAccountDTO.getAge() == null || userAccountDTO.getAge() < 18) {
                throw new IllegalArgumentException("Debes ser mayor de edad para registrarte");
            }

            if (userAccountRepository.existsByUsername(userAccountDTO.getUsername())) {
                throw new IllegalArgumentException("El nombre de usuario ya está en uso");
            }

            if (userAccountRepository.existsByEmail(userAccountDTO.getEmail())) {
                throw new IllegalArgumentException("El email ya está registrado");
            }
    }

}
