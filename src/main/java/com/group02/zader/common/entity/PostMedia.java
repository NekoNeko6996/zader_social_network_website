package com.group02.zader.common.entity;

import com.group02.zader.common.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "postMedia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMedia extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MediaType mediaType;

    @Column(nullable = false, length = 100)
    private String mediaUrl;
}