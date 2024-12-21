package com.cheeup.domain.portfolio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "portfolio_projects")
public class PortfolioProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String outline;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String result;

    @Column(nullable = false)
    private String githubUrl;
}
