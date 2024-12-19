package com.cheeup.domain;

import com.cheeup.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PortfolioMilitaries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryType militaryType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryBranch militaryBranch;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitarySpecialty militarySpecialty;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryRank militaryRank;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryDischarge militaryDischarge;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;
}
