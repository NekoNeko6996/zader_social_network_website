package com.group02.zader.auth;


import com.group02.zader.common.entity.Member;
import com.group02.zader.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // Tiêm (Inject) UserRepository vào qua Constructor
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Phương thức chính mà Spring Security gọi trong quá trình đăng nhập.
     * Tên đăng nhập được dùng ở đây chính là username hoặc email.
     */
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // 1. Tìm kiếm Member trong DB bằng username
        Member member = userRepository.findByUsername(identifier)
                .orElseGet(() -> userRepository.findByEmail(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with identifier: " + identifier)));

        // 2. Trả về đối tượng UserDetails của Spring Security.
        // Đối tượng này chứa: username, password (đã mã hóa) và Authority (quyền hạn/role).
        return new org.springframework.security.core.userdetails.User(
                member.getUsername(), // Tên đăng nhập
                member.getPasswordHash(), // Mật khẩu (đã mã hóa)
                Collections.singleton(member.getRole().toGrantedAuthority()) // Quyền hạn (ví dụ: ROLE_USER, ROLE_ADMIN)
        );
    }
}