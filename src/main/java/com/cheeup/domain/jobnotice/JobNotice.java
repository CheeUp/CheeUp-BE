package com.cheeup.domain.jobnotice;

import com.cheeup.domain.enums.CompanySize;
import com.cheeup.domain.member.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@ToString
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

    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Column(nullable = false, length = 255)
    private String url;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
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
    private List<JobNoticeFile> jobNoticeFileList = new ArrayList<>();

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobDescription> jobDescriptionList = new ArrayList<>();

    @OneToMany(mappedBy = "jobNotice", cascade = CascadeType.ALL)
    private List<JobNoticeJob> jobNoticeJobList = new ArrayList<>();


    public void setJobNoticeJobList(List<JobNoticeJob> jobNoticeJobList) {
        this.jobNoticeJobList = jobNoticeJobList;
    }

    public void addJobDescription(JobDescription jobDescription) {
        this.jobDescriptionList.add(jobDescription);
        jobDescription.setJobNotice(this); // 자식 엔티티에 부모 설정
    }
}
