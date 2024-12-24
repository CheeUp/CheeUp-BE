package com.cheeup.web.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JoinJobNoticeDTO {

    @Getter
    @Setter
    public static class RequestDTO {
        String title;
        List<PositionDTO> positions;
        String company;
        Integer companySize;
        String url;
        LocalDate startDate;
        LocalDate endDate;
        List<ImageDTO> images;
        List<JobDescriptionDTO> jobDescriptions;
    }

    @Getter
    @Setter
    public static class PositionDTO {
        Long id;
        String name;
    }

    @Getter
    @Setter
    public static class ImageDTO {
        String name;
        Short size;
        String type;
        String url;
    }

    @Getter
    @Setter
    public static class JobDescriptionDTO {
        String title;
        String type;
        String location;
        String description;
        String requirement;
        String preferredRequirement;
        List<SkillDTO> skills;
    }

    @Getter
    @Setter
    public static class SkillDTO {
        Long id;
        String name;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDTO {

    }
}
