package com.group18.familyhealthcare.controller;

import com.group18.familyhealthcare.entity.UserAccount;
import com.group18.familyhealthcare.service.UserAccountService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class UserAccountControllerTest {

    @Mock
    private UserAccountService accountService;

    @Test
    void testAddNewAccount() {
        // Setup
        UserAccount account = new UserAccount("jim@example.com", "password789");
        when(accountService.addUserAccount(any(UserAccount.class))).thenReturn(account);

        UserAccountController controller = new UserAccountController(accountService);

        // Act
        ResponseEntity<Object> response = controller.addNewAccount(account);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Account created successfully", response.getBody().toString());
    }

    @Test
    void testAccountLogin() {
        // Setup
        UserAccount account = new UserAccount("jim@example.com", "password789");
        when(accountService.accountLogin(any(UserAccount.class))).thenReturn(account);

        UserAccountController controller = new UserAccountController(accountService);

        // Act
        ResponseEntity<Object> response = controller.accountLogin(account);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Login successfully", response.getBody().toString());
    }

}


