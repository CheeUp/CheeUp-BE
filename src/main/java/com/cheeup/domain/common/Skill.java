package com.cheeup.domain.common;

import com.cheeup.domain.job.JobDescriptionSkill;
import com.cheeup.domain.member.MemberSkill;
import com.cheeup.domain.portfolio.PortfolioProjectSkill;
import com.cheeup.domain.portfolio.PortfolioSkill;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<MemberSkill> memberSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<PortfolioSkill> portfolioSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<PortfolioProjectSkill> portfolioProjectSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<JobDescriptionSkill> jobDescriptionSkills = new ArrayList<>();
}

