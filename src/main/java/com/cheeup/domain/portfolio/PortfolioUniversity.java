package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.UniversityCampus;
import com.cheeup.domain.enums.UniversityEntry;
import com.cheeup.domain.enums.UniversityStatus;
import com.cheeup.domain.enums.UniversityType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_universities")
public class PortfolioUniversity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    private String region;

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
    private Date startDate;

    private Date endDate;

    private Date leaveDate;
}
