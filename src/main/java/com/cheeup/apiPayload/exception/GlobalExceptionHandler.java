package com.cheeup.apiPayload.exception;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.error.codes.ValidationErrorCode;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<ApiResponse<?>> handleException(ServiceException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(ApiResponse.onFailure(e.getErrorCode()));
    }

    // JSON 형식이 잘못됐을때 발생하는 Exception handler
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {

        String code = "COMMON_400";
        String message = "JSON 형식이 잘못 됐습니다.";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.onFailure(code, message));
    }

    // @Valid 로 검증 실패했을때 발생하는 Exception handler.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();

        String code = "COMMON_400";
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.onFailure(code, message));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatusCode statusCode, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors().stream()
                .forEach(fieldError -> {
                    String fieldName = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                    errors.merge(fieldName, errorMessage,
                            (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
                });

        String errorCode = errors.isEmpty() ? null : errors.values().iterator().next();

        return handleExceptionInternalArgs(e, HttpHeaders.EMPTY, ValidationErrorCode.valueOf(errorCode), request,
                errors);
    }

    private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers,
                                                               ValidationErrorCode validationErrorCode,
                                                               WebRequest request, Map<String, String> errorArgs) {

        ApiResponse<Object> body = ApiResponse.onFailure(validationErrorCode);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                validationErrorCode.getHttpStatus(),
                request
        );
    }
}


