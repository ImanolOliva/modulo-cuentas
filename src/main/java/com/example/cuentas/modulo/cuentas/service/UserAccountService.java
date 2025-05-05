package com.example.cuentas.modulo.cuentas.service;

import com.example.cuentas.modulo.cuentas.dto.UserAccountDTO;
import org.springframework.stereotype.Service;

public interface UserAccountService {


    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO);
}
