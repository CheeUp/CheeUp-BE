package com.cheeup.domain.member;

import com.cheeup.domain.community.FavoriteBoard;
import com.cheeup.domain.community.Post;
import com.cheeup.domain.enums.MemberRole;
import com.cheeup.domain.enums.Tier;
import com.cheeup.domain.portfolio.Portfolio;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "members")
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

    //기수 컬럼: "SSAFY 12기", "SSAFY 11기"
    @Column(length = 20)
    private String groups;

    @Column( length = 20)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'GUEST'")
    private MemberRole role;

    @Column(length = 225)
    private String githubUrl;

    @Column(length = 225)
    private String profileImageUrl;

    @Column
    @ColumnDefault("true")
    private Boolean isActivated;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer experience = 0;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'BRONZE'")
    private Tier tier;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFile> memberFileList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<FavoriteBoard> favoriteBoardList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> postList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Portfolio> portfolioList;

}

