package com.japan.demo.service.impl;

import com.japan.demo.repository.RoleRepository;
import com.japan.demo.model.User;
import com.japan.demo.repository.UserRepository;
import com.japan.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Collections.singleton(role)));
        return userRepository.save(user);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByCurrentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow(() -> new EntityNotFoundException("Username not found"));
    }
}
