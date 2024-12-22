package com.cheeup.domain.job;

import com.cheeup.domain.member.Member;
import com.cheeup.domain.portfolio.Portfolio;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "jot_notices")
public class JobNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @Column(length = 30)
    private String title;

    @Column(nullable = false, length = 30)
    private String company;

    @Column(nullable = false, length = 30)
    private String position;

    private Integer companySize;

    @Column(nullable = false, length = 255)
    private String url;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private Integer like = 0;

    @Column(nullable = false)
    private Integer scrap = 0;

    @Column(nullable = false)
    private Integer hit = 0;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobNoticeFile> jobNoticeFileList = new ArrayList<>();

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobNoticeLike> jobNoticeLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobNoticeScrap> jobNoticeScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobDescription> jobDescriptionList = new ArrayList<>();
}
