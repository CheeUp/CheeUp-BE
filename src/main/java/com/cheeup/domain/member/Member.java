package com.cheeup.domain.member;

import com.cheeup.domain.enums.MemberRole;
import com.cheeup.domain.enums.Tier;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String kakaoId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 20, unique = true)
    private String nickname;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private MemberRole role = MemberRole.GUEST;

    @Column(length = 225)
    private String githubLink;

    @Column(length = 225)
    private String profileImageUrl;

    @Column(nullable = false)
    private Boolean isActivated = true;

    @Column(nullable = false)
    private Integer experience = 0;

    @Enumerated(EnumType.STRING)
    private Tier tier = Tier.BRONZE;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberActivity> memberActivityList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberSkill> memberSkillList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<InactiveMember> inactiveMemberList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFile> memberFileList;

}

