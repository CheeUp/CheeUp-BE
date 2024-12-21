package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.DisabilityType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_disabilities")
public class PortfolioDisability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Portfolio portfolio;


    @Column(nullable = false)
    private Boolean status;

    @Enumerated(EnumType.STRING)
    private DisabilityType type;

    private Integer grade;
}

