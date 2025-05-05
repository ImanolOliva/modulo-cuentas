package com.example.cuentas.modulo.cuentas.controller;
import com.example.cuentas.modulo.cuentas.dto.UserAccountDTO;
import com.example.cuentas.modulo.cuentas.service.impl.UserAccountServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class CuentasController {



    @Autowired
    UserAccountServiceImpl userAccountService;

    @PostMapping("/create")
    public ResponseEntity<UserAccountDTO> createUser(@Valid @RequestBody  UserAccountDTO userAccountDTO){
            return new ResponseEntity<>(this.userAccountService.createUserAccount(userAccountDTO), HttpStatus.valueOf(200));
    }
}
