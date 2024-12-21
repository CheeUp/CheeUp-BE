package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private Date startDate;

    private Date endDate;
}
