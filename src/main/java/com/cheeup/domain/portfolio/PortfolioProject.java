package com.cheeup.domain.portfolio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_projects")
public class PortfolioProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Portfolio portfolio;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String outline;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String result;

    @Column(nullable = false, length = 255)
    private String githubUrl;

    @Builder.Default
    @OneToMany(mappedBy = "portfolioProject", cascade = CascadeType.ALL)
    private List<PortfolioProjectSkill> portfolioProjectSkillList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "portfolioProject", cascade = CascadeType.ALL)
    private List<PortfolioProjectFile> portfolioProjectFileList = new ArrayList<>();
}
