package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.HighSchoolMajor;
import com.cheeup.domain.enums.HighSchoolType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_high_schools")
public class PortfolioHighSchool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HighSchoolMajor major;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HighSchoolType type;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;
}
