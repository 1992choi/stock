package com.example.stock.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReq {

    private String userEmail;

    private String userPassword;

    private String userName;

    public User toEntity() {
        return User.builder()
                .userEmail(this.userEmail)
                .userPassword(this.userPassword)
                .userName(this.userName)
                .build();
    }

}
