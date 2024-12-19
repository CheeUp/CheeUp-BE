package com.cheeup.domain;

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
    private Integer type;

    @Column(nullable = false)
    private Integer branch;

    @Column(nullable = false)
    private Integer specialty;

    @Column(nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private Integer dischargeType;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;
}
