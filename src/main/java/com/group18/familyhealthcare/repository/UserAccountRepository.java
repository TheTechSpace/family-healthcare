package com.group18.familyhealthcare.repository;

import com.group18.familyhealthcare.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findUserAccountByEmail(String username);

    Optional<UserAccount> findUserAccountByEmailAndAndPassword(String email, String password);

    UserAccount findUserAccountByAccountId(Long accountId);
}
