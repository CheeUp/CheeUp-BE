package com.cheeup.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PortfolioGraduates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    private String region;

    @Column(nullable = false)
    private Integer degreeType;

    @Column(nullable = false)
    private Integer campusType;

    @Column(nullable = false)
    private Integer entryType;

    @Column(nullable = false)
    private Integer graduatedType;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;
}
