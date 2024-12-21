package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.TrainingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_trainings")
public class PortfolioTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Portfolio portfolio;


    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20)
    private String company;

    private String description;

    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TrainingStatus status;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;

}


