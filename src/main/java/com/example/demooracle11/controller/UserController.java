package com.example.demooracle11.controller;

import com.example.demooracle11.entities.User;
import com.example.demooracle11.services.UserService;
import com.example.demooracle11.utils.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @GetMapping
    public List<User> getAllUsers(HttpServletRequest request) {
        loggingInterceptor.logRequestInfo(request, null);
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(HttpServletRequest request, @PathVariable Long id) {
        loggingInterceptor.logRequestInfo(request, null);
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(HttpServletRequest request, @RequestBody User user) {
        loggingInterceptor.logRequestInfo(request, user);
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(HttpServletRequest request, @PathVariable Long id, @RequestBody User userDetails) {
        loggingInterceptor.logUpdateInfo(request, userDetails);
        User updatedUser = userService.updateUser(id, userDetails);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request, @PathVariable Long id) {
        loggingInterceptor.logDeleteInfo(request, id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
