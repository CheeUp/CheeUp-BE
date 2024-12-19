package com.cheeup.domain;

import com.cheeup.domain.enums.TrainingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PortfolioTrainings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20)
    private String company;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TrainingStatus trainingStatus;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;

    private String content;
}


