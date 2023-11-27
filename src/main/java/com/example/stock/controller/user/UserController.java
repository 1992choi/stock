package com.example.stock.controller.user;

import com.example.stock.domain.user.User;
import com.example.stock.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> findUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> findUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

}
