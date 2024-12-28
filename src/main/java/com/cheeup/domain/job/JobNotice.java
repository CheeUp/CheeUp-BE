package com.cheeup.domain.job;

import com.cheeup.domain.member.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "job_notices")
public class JobNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @Column(length = 30)
    private String title;

    @Column(nullable = false, length = 30)
    private String company;

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobNoticeJob> position = new ArrayList<>();

    private Integer companySize;

    @Column(nullable = false, length = 255)
    private String url;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer likes;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer scraps;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer hits;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobNoticeFile> jobNoticeFileList;

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobDescription> jobDescriptionList;

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobNoticeJob> jobNoticeJobList;
}
