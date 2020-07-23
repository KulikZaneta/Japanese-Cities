package com.japan.demo.service;

import com.japan.demo.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    User save(User user);

    Page<User> getPage(Pageable pageable);

    User findByCurrentUser();
}
