package com.cheeup.domain;

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
    private Integer status;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    private Integer description;
}

