package com.cheeup.domain.portfolio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PortfolioBasicInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer status;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Integer type;

}
