package com.example.stock.domain.stock;

import com.example.stock.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity(name = "TB_BOOKMARK")
@Getter
@ToString
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKMARK_ID")
    private Long bookmarkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "STOCK_ID")
    private Long stockId;

    @Column(name = "ISSUED_FLAG")
    private String issuedFlag;

}
