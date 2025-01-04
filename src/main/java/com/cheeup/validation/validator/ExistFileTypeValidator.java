package com.cheeup.validation.validator;

import com.cheeup.apiPayload.code.error.codes.ValidationErrorCode;
import com.cheeup.domain.enums.FileType;
import com.cheeup.validation.annotation.ExistFileType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistFileTypeValidator implements ConstraintValidator<ExistFileType, String> {


    @Override
    public void initialize(ExistFileType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid;
        if (value == null) {
            isValid = false;
        } else {
            isValid = Arrays.stream(FileType.values())
                    .anyMatch(fileType -> fileType.name().equals(value));
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidationErrorCode.NOT_FOUND_FILE_TYPE.getMessage())
                    .addConstraintViolation();
        }

        return isValid;

    }
}
