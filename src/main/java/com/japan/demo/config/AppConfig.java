package com.japan.demo.config;

import com.japan.demo.repository.RoleRepository;
import com.japan.demo.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
            Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            if (!roleUser.isPresent()) {
                roleRepository.save(Role.builder().name("ROLE_USER").build());
            }
            if (!roleAdmin.isPresent()) {
                roleRepository.save(Role.builder().name("ROLE_ADMIN").build());
            }
        };
    }
}
