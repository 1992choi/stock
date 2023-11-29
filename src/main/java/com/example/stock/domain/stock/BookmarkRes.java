package com.example.stock.domain.stock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkRes {

    private Long bookmarkId;
    private StockRes stock;
    private String issuedFlag;

    public BookmarkRes(Bookmark bookmark) {
        this.bookmarkId = bookmark.getBookmarkId();
        this.stock = new StockRes(bookmark.getStock());
        this.issuedFlag = bookmark.getIssuedFlag();
    }

}
