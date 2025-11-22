package com.group02.zader.common.entity;

import com.group02.zader.common.enums.ReportStatus;
import com.group02.zader.common.enums.TargetType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporterId", nullable = false)
    private Member reporter;

    @Column(nullable = false)
    private Long targetId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TargetType targetType;

    @Column(nullable = false)
    private String reason;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String adminNotes;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ReportStatus status;
}