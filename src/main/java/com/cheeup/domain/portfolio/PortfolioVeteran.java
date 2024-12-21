package com.cheeup.domain.portfolio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_veterans")
public class PortfolioVeteran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Portfolio portfolio;


    @Column(nullable = false)
    private Boolean status;

    @Column(length = 20)
    private String relation;

    @Column(length = 20)
    private String number;

    private Integer ratio;
}
