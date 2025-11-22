package com.group02.zader.common.entity;

import com.group02.zader.common.enums.Role;
import com.group02.zader.common.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "members") // Khớp tên bảng trong SQL
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 64)
    private String passwordHash;

    @Column(unique = true, length = 50)
    private String email;

    @Column(unique = true, length = 15)
    private String phone;

    @Column(length = 100)
    private String fullName;

    @Column(length = 200)
    private String avatarUrl;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserStatus status;
}