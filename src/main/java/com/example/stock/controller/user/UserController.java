package com.example.stock.controller.user;

import com.example.stock.common.jwt.TokenProvider;
import com.example.stock.domain.common.CommonRes;
import com.example.stock.domain.user.UserReq;
import com.example.stock.domain.user.UserRes;
import com.example.stock.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final TokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<CommonRes> login(@RequestBody UserReq userReq) {
        UserRes userRes = userService.login(userReq);
        userRes.addToken(tokenProvider.createToken(userRes.getUserEmail()));
        return ResponseEntity.ok(CommonRes.successRes(userRes));
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
    public ResponseEntity<CommonRes> saveUser(@RequestBody UserReq userReq) {
        return ResponseEntity.ok(CommonRes.successRes(userService.saveUser(userReq)));
    }

    @GetMapping("/api/users/{userId}/bookmarks")
    public ResponseEntity<CommonRes> findBookmarks(@PathVariable Long userId) {
        return ResponseEntity.ok(CommonRes.successRes(userService.findBookmarks(userId)));
    }

}
