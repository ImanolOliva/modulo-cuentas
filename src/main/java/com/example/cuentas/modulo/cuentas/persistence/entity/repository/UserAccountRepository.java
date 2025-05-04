package com.example.cuentas.modulo.cuentas.persistence.entity.repository;

import com.example.cuentas.modulo.cuentas.persistence.entity.users.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
