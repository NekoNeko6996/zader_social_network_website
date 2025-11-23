package com.group02.zader.common.entity;

import com.group02.zader.common.enums.PostVisibility;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PostVisibility visibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "originalPostId")
    private Post originalPost;

    // Để lấy danh sách media từ post: post.getPostMediaList()
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostMedia> postMediaList;
}