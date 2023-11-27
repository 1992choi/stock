package com.example.stock.service.user;

import com.example.stock.domain.user.User;
import com.example.stock.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseGet(User::new);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
