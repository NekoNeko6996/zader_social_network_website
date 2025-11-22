package com.group02.zader.common.entity;

import com.group02.zader.common.enums.PostVisibility;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    // Quan hệ với User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PostVisibility visibility;

    // Chia sẻ bài viết (Recursive)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "originalPostId")
    private Post originalPost;
}