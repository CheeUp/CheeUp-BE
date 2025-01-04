package com.cheeup.validation.validator;

import com.cheeup.apiPayload.code.error.codes.ValidationErrorCode;
import com.cheeup.domain.enums.JobDescriptionType;
import com.cheeup.validation.annotation.ExistJobDescriptionType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistJobDescriptionTypeValidator implements ConstraintValidator<ExistJobDescriptionType, String> {


    @Override
    public void initialize(ExistJobDescriptionType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid;
        System.out.println("==========================================================");
        System.out.println(value);
        if (value == null) {
            isValid = false;
        } else {
            isValid = Arrays.stream(JobDescriptionType.values())
                    .anyMatch(jobDescriptionType -> jobDescriptionType.name().equals(value));
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            ValidationErrorCode.NOT_FOUND_JOB_DESCRIPTION_TYPE.getMessage())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
