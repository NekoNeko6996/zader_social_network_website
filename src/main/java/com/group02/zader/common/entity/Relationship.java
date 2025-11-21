package com.group02.zader.common.entity;


import com.group02.zader.common.enums.RelationshipStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "relationships", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userOneId", "userTwoId"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relationship extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relationshipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userOneId", nullable = false)
    private Member userOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userTwoId", nullable = false)
    private Member userTwo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RelationshipStatus status;
}