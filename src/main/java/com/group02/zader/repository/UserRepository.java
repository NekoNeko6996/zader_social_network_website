package com.group02.zader.repository;

import com.group02.zader.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByEmail(String email);
    
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
