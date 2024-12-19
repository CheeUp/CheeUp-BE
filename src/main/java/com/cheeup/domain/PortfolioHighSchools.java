package com.cheeup.domain;

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
public class PortfolioHighSchools {

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
