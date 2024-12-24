package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.MajorTime;
import com.cheeup.domain.enums.MajorType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_graduate_majors")
public class PortfolioGraduateMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_graduate_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PortfolioGraduate portfolioGraduate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MajorType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MajorTime time;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal grade;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal maxGrade;
}

