package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.MilitaryBranch;
import com.cheeup.domain.enums.MilitaryDischarge;
import com.cheeup.domain.enums.MilitaryRank;
import com.cheeup.domain.enums.MilitarySpecialty;
import com.cheeup.domain.enums.MilitaryType;
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
import java.time.LocalDate;
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
@Table(name = "portfolio_militaries")
public class PortfolioMilitary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Portfolio portfolio;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryBranch branch;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitarySpecialty specialty;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryRank rank;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryDischarge discharge;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;
}
