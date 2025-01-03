package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.GraduateDegree;
import com.cheeup.domain.enums.UniversityCampus;
import com.cheeup.domain.enums.UniversityEntry;
import com.cheeup.domain.enums.UniversityStatus;
import com.cheeup.domain.enums.UniversityType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
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
@Table(name = "portfolio_graduates")
public class PortfolioGraduate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Portfolio portfolio;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    private String region;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GraduateDegree degree;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UniversityType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UniversityCampus campus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UniversityEntry entry;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UniversityStatus status;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate leaveDate;

    @Builder.Default
    @OneToMany(mappedBy = "portfolioGraduate", cascade = CascadeType.ALL)
    private List<PortfolioGraduateMajor> portfolioGraduateMajorList = new ArrayList<>();
}
