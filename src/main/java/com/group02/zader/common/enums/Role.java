package com.group02.zader.common.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role { 
    // Các giá trị Role được định nghĩa
    USER, 
    ADMIN;

    /**
     * Chuyển đổi Enum Role sang đối tượng GrantedAuthority mà Spring Security cần.
     * Thêm prefix "ROLE_" theo quy ước của Spring Security cho các quyền hạn.
     */
    public GrantedAuthority toGrantedAuthority() {
        // Tên role (ví dụ: "USER") được chuyển thành "ROLE_USER"
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }
}