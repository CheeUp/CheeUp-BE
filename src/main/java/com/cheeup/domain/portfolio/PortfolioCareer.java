package com.cheeup.domain.portfolio;

import com.cheeup.domain.enums.CareerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_careers")
public class PortfolioCareer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CareerStatus status;

    @Column(nullable = false, length = 20)
    private String company;

    @Column(nullable = false, length = 30)
    private String role;

    private String description;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;

    private Date leaveDate;
}
