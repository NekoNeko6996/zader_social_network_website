package com.group02.zader.auth;

import com.group02.zader.common.entity.Member;
import com.group02.zader.common.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data; // Lombok giúp sinh getter
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(member.getRole().toGrantedAuthority());
    }

    @Override
    public String getPassword() {
        return member.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }
    
    public String getAvatarUrl() {
        return member.getAvatarUrl();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() {
        return member.getStatus() == UserStatus.ACTIVE; 
    }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}