package com.example.stock.domain.user;

import com.example.stock.domain.stock.BookmarkRes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserRes {

    private Long userId;

    private String userEmail;

    private String userName;

    private String token;

    private List<BookmarkRes> bookmarks = new ArrayList<>();

    public UserRes(User user) {
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.bookmarks = user.getBookmarks().stream()
                .map(BookmarkRes::new)
                .collect(Collectors.toList());
    }

    public void addToken(String token) {
        this.token = token;
    }

}
