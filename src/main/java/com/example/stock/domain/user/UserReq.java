package com.example.stock.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReq {

    private String userEmail;

    private String userPassword;

}
