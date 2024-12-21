package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.GraduateDegree;
import com.cheeup.domain.enums.UniversityCampus;
import com.cheeup.domain.enums.UniversityEntry;
import com.cheeup.domain.enums.UniversityStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private UniversityCampus campus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UniversityEntry entry;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UniversityStatus status;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;

    private Date leaveDate;

    @OneToMany(mappedBy = "portfolioGraduate", cascade = CascadeType.ALL)
    private List<PortfolioGraduateMajor> portfolioGraduateMajorList = new ArrayList<>();
}
