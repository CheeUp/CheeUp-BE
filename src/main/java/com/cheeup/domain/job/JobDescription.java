package com.cheeup.domain.job;

import com.cheeup.domain.enums.JobDescriptionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "job_descriptions")
public class JobDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobDescriptionType type;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String location;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String requirement;

    private String preferredRequirement;
}
