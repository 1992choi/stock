package com.example.stock.domain.stock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkRes {

    private Long bookmarkId;
    private Long stockId;
    private String issuedFlag;

    public BookmarkRes(Bookmark bookmark) {
        this.bookmarkId = bookmark.getBookmarkId();
        this.stockId = bookmark.getStockId();
        this.issuedFlag = bookmark.getIssuedFlag();
    }

}
