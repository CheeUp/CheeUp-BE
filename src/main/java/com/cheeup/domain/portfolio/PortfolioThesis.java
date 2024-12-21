package com.cheeup.domain.portfolio;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_theses")
public class PortfolioThesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 100)
    private String subTitle;

    private String summary;

    @Column(length = 30)
    private String submissionTarget;

    @Column(nullable = false)
    private Date submissionDate;
}
