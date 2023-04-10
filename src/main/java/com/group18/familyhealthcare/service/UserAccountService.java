package com.group18.familyhealthcare.service;

import com.group18.familyhealthcare.entity.UserAccount;
import com.group18.familyhealthcare.repository.UserAccountRepository;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {
    private final UserAccountRepository accountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<UserAccount> getAllAccount() {
        return accountRepository.findAll();
    }

    public Object addUserAccount(UserAccount userAccount){
        HashMap<String, Object> data = new HashMap<>();
        Optional<UserAccount> userAccountByEmail = accountRepository.findUserAccountByEmail(
                userAccount.getEmail());
        if (userAccountByEmail.isPresent()){
            throw new IllegalStateException("Email already exist");
        }
        userAccount.setPassword(passwordEncrypt(userAccount.getPassword()));
        UserAccount user = accountRepository.save(userAccount);
        System.out.println("user = " + user);
        data.put("id", user.getAccountId());
        data.put("email", user.getEmail());

        return data;
    }

     public Object accountLogin(UserAccount userAccount){
         HashMap<String, Object> data = new HashMap<>();
         Optional<UserAccount> authUser = accountRepository.findUserAccountByEmailAndAndPassword(
                 userAccount.getEmail(), passwordEncrypt(userAccount.getPassword()));

         if (authUser.isPresent()){
             data.put("id", authUser.get().getAccountId());
             data.put("email", authUser.get().getEmail());
         }else {
             throw new IllegalStateException("Wrong email or password.");
         }
         return data;
     }

    private String passwordEncrypt(String password)  {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        final byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = HexUtils.toHexString(hashBytes);
        return sha3Hex;
    }
}
