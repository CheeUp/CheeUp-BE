package com.cheeup.domain.portfolio;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private String githubUrl;

    @OneToMany(mappedBy = "portfolioProject", cascade = CascadeType.ALL)
    private List<PortfolioProjectSkill> portfolioProjectSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "portfolioProject", cascade = CascadeType.ALL)
    private List<PortfolioProjectFile> portfolioProjectFileList = new ArrayList<>();
}
