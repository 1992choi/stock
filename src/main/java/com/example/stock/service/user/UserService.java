package com.example.stock.service.user;

import com.example.stock.domain.stock.Bookmark;
import com.example.stock.domain.user.User;
import com.example.stock.repository.stock.BookmarkRepository;
import com.example.stock.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseGet(User::new);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<Bookmark> findBookmarks(Long userId) {
        User user = userRepository.findById(userId).get();
        return bookmarkRepository.findAllByUser(user);
    }

}
