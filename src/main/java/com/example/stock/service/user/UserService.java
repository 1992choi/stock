package com.example.stock.service.user;

import com.example.stock.domain.stock.Bookmark;
import com.example.stock.domain.stock.BookmarkRes;
import com.example.stock.domain.user.User;
import com.example.stock.domain.user.UserReq;
import com.example.stock.domain.user.UserRes;
import com.example.stock.repository.stock.BookmarkRepository;
import com.example.stock.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;

    public UserRes login(UserReq userReq) {
        User user = userRepository.findByUserEmail(userReq.getUserEmail()).orElseThrow(() -> new NoSuchElementException("사용자가 없습니다."));
        if (!user.getUserPassword().equals(userReq.getUserPassword())) {
            new NoSuchElementException("사용자가 없습니다.");
        }

        return new UserRes(user);
    }

    public List<UserRes> findUsers() {
        return userRepository.findAll().stream()
                .map(UserRes::new)
                .collect(Collectors.toList());
    }

    public UserRes findUser(Long userId) {
        return new UserRes(userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("사용자가 없습니다.")));
    }

    @Transactional
    public UserRes saveUser(UserReq userReq) {
        return new UserRes(userRepository.save(userReq.toEntity()));
    }

    public List<BookmarkRes> findBookmarks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("사용자가 없습니다."));
        List<Bookmark> bookmarks = bookmarkRepository.findAllByUser(user);

        return bookmarks.stream()
                .map(BookmarkRes::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new NoSuchElementException("사용자가 없습니다."));

        return new org.springframework.security.core.userdetails.User(
                user.getUserEmail(),
                user.getUserPassword(),
                Arrays.asList("ADMIN").stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()) // todo. 권한처리
        );
    }

}
