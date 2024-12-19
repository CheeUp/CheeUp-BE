package com.cheeup.domain;

import com.cheeup.domain.enums.DisabilityType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PortfolioDisabilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DisabilityType disabilityType;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    private Integer description;
}

