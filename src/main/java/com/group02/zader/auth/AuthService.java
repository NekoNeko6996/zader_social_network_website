package com.group02.zader.auth;


import com.group02.zader.common.dto.RegisterDTO;
import com.group02.zader.common.entity.Member;
import com.group02.zader.common.enums.Role;
import com.group02.zader.common.enums.UserStatus;
import com.group02.zader.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // inject
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterDTO registerDto) throws Exception {
        
        // check
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new Exception("Username already exists!");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new Exception("Email already exists!");
        }
        
        // create new member
        Member newMember = Member.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .passwordHash(passwordEncoder.encode(registerDto.getPassword()))
                .role(Role.USER) 
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();
        
        // save
        userRepository.save(newMember);
    }
}