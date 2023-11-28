package com.example.stock.repository.stock;

import com.example.stock.domain.stock.Bookmark;
import com.example.stock.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findAllByUser(User user);

}
