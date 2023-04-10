package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.utility.ResponseHandler;
import com.group18.familyhealthcare.entity.UserAccount;
import com.group18.familyhealthcare.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
public class UserAccountController {
    private final UserAccountService accountService;

    @Autowired
    public UserAccountController(UserAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/list")
    public List<UserAccount> getAllAccount(){
        return accountService.getAllAccount();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> addNewAccount(@RequestBody UserAccount account){
        try {
            Object data = accountService.addUserAccount(account);
            return ResponseHandler.generateResponse("Account created successfully", HttpStatus.OK, data);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> accountLogin(@RequestBody UserAccount account){
        try {
            Object data = accountService.accountLogin(account);
            return ResponseHandler.generateResponse("Login successfully", HttpStatus.OK, data);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
