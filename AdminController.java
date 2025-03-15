package com.example.web.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.service.ServiceInterface;
import com.example.web.user.User;

@RestController
@RequestMapping("/BankApiSystem/admin")
public class AdminController {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "adminpass123";

    @Autowired
    private ServiceInterface service;
    
    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts(@RequestHeader(value = "username", required = false) String username,
                                         @RequestHeader(value = "password", required = false) String password) {
        // Basic authentication check
        if (username == null || password == null || 
            !username.equals(ADMIN_USERNAME) || !password.equals(ADMIN_PASSWORD)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Admin credentials required");
        }
        
        List<User> accounts = service.getAllAccount();
        return ResponseEntity.ok(accounts);
    }
    
    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard(@RequestHeader(value = "username", required = false) String username,
                                       @RequestHeader(value = "password", required = false) String password) {
        // Basic authentication check
        if (username == null || password == null || 
            !username.equals(ADMIN_USERNAME) || !password.equals(ADMIN_PASSWORD)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Admin credentials required");
        }
        
        // Get all accounts
        List<User> accounts = service.getAllAccount();
        
        // Create a dashboard summary
        int totalAccounts = accounts.size();
        
        return ResponseEntity.ok("Admin Dashboard\n" +
                "Total Accounts: " + totalAccounts);
    }
}