package com.example.stock.service.user;

import com.example.stock.domain.stock.Bookmark;
import com.example.stock.domain.stock.BookmarkRes;
import com.example.stock.domain.user.User;
import com.example.stock.domain.user.UserRes;
import com.example.stock.repository.stock.BookmarkRepository;
import com.example.stock.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;

    public List<UserRes> findUsers() {
        return userRepository.findAll().stream()
                .map(UserRes::new)
                .collect(Collectors.toList());
    }

    public UserRes findUser(Long userId) {
        return new UserRes(userRepository.findById(userId).orElseGet(User::new));
    }

    @Transactional
    public UserRes saveUser(User user) {
        return new UserRes(userRepository.save(user));
    }

    public List<BookmarkRes> findBookmarks(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Bookmark> bookmarks = bookmarkRepository.findAllByUser(user);

        return bookmarks.stream()
                .map(BookmarkRes::new)
                .collect(Collectors.toList());
    }

}
