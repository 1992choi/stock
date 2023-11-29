package com.example.stock.controller.user;

import com.example.stock.domain.stock.BookmarkRes;
import com.example.stock.domain.user.User;
import com.example.stock.domain.user.UserRes;
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
    public ResponseEntity<List<UserRes>> findUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserRes> findUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserRes> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/api/users/{userId}/bookmarks")
    public ResponseEntity<List<BookmarkRes>> findBookmarks(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findBookmarks(userId));
    }

}
