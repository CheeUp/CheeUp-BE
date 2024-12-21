package com.cheeup.domain.common;

import com.cheeup.domain.portfolio.Portfolio;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Portfolio> portfolioList = new ArrayList<>();
}
