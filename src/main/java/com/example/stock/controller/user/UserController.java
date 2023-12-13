package com.example.stock.controller.user;

import com.example.stock.domain.common.CommonRes;
import com.example.stock.domain.user.User;
import com.example.stock.domain.user.UserReq;
import com.example.stock.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<CommonRes> login(@RequestBody UserReq userReq) {
        return ResponseEntity.ok(CommonRes.successRes(userService.login(userReq)));
    }

    @GetMapping("/api/users")
    public ResponseEntity<CommonRes> findUsers() {
        return ResponseEntity.ok(CommonRes.successRes(userService.findUsers()));
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<CommonRes> findUser(@PathVariable Long userId) {
        return ResponseEntity.ok(CommonRes.successRes(userService.findUser(userId)));
    }

    @PostMapping("/api/users")
    public ResponseEntity<CommonRes> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(CommonRes.successRes(userService.saveUser(user)));
    }

    @GetMapping("/api/users/{userId}/bookmarks")
    public ResponseEntity<CommonRes> findBookmarks(@PathVariable Long userId) {
        return ResponseEntity.ok(CommonRes.successRes(userService.findBookmarks(userId)));
    }

}
