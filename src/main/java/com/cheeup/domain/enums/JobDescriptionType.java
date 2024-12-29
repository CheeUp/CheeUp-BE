package com.cheeup.domain.enums;

import java.util.Arrays;

public enum JobDescriptionType {
    신입, 경력, 인턴;

    public static boolean isValidType(String type) {
        return Arrays.stream(JobDescriptionType.values())
                .anyMatch(enumValue -> enumValue.name().equals(type));
    }
}
