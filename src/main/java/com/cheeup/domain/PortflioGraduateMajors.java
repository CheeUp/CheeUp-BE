package com.cheeup.domain;

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
public class PortflioGraduateMajors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MajorType majorType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MajorTime majorTime;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal grade;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal maxGrade;
}

