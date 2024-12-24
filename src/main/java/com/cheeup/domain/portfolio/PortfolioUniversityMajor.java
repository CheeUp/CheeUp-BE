package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.MajorTime;
import com.cheeup.domain.enums.MajorType;
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
import jakarta.persistence.Table;
import java.math.BigDecimal;
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
@Table(name = "portfolio_university_majors")
public class PortfolioUniversityMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_university_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PortfolioUniversity portfolioUniversity;

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

