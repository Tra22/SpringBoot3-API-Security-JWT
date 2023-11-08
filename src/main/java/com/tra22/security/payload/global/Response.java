package com.tra22.security.payload.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tra22.security.constant.application.AppStatusCode;
import com.tra22.security.exception.AppException;
import com.tra22.security.exception.NotFoundEntityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private T data;
    private MetaData metadata;
    private String message;
    private String code;
    public static Response<?> ok(Object data){
        return Response
                .builder()
                .data(data)
                .message("Success to get response.")
                .code(AppStatusCode.SUCCESS.getCode())
                .build();
    }
    public static Response<?> okWithNoContent(){
        return Response
                .builder()
                .message("Success with no content.")
                .code(AppStatusCode.SUCCESS.getCode())
                .build();
    }
    public static Response<?> authenticationHandle(AuthenticationException e, HttpServletRequest httpServletRequest) {
        return Response.builder()
                .code(AppStatusCode.AUTHENTICATION.getCode())
                .metadata(getCurrentMetaData(httpServletRequest))
                .data(ErrorContent.build(getAuthenticationMessage(e), e.getMessage()))
                .build();
    }

    public static Response<?> notFoundEntityResponse(NotFoundEntityException exception, HttpServletRequest httpServletRequest){
        return Response
                .builder()
                .metadata(getCurrentMetaData(httpServletRequest))
                .message(exception.getMessage())
                .code(AppStatusCode.NOT_FOUND_ENTITY.getCode())
                .build();
    }
    public static Response<?> handleAppException(AppException exception, HttpServletRequest httpServletRequest){
        return Response
                .builder()
                .metadata(getCurrentMetaData(httpServletRequest))
                .message(exception.getMessage())
                .code(AppStatusCode.APP_ERR.getCode())
                .build();
    }
    public static Response<?> unhandledException(Exception exception, HttpServletRequest httpServletRequest){
        return Response
                .builder()
                .metadata(getCurrentMetaData(httpServletRequest))
                .message("Unexpected error.")
                .code(AppStatusCode.UNEXPECTED_ERR.getCode())
                .build();
    }
    public static Response<?> unhandledException(HttpServletRequest httpServletRequest){
        return Response
                .builder()
                .metadata(getCurrentMetaData(httpServletRequest))
                .message("Unexpected error.")
                .code(AppStatusCode.UNEXPECTED_ERR.getCode())
                .build();
    }
    private static MetaData getCurrentMetaData(HttpServletRequest httpServletRequest){
        return  MetaData
                        .builder()
                        .path(httpServletRequest.getServletPath())
                        .timestamp(new Date())
                        .build();
    }

    private static String getAuthenticationMessage(AuthenticationException e) {
        if (e instanceof InsufficientAuthenticationException) {
            return "app.common.exception.authentication.insufficient";
        }
        if (e instanceof AccountExpiredException) {
            return "app.common.exception.authentication.account-expired";
        }
        if (e instanceof CredentialsExpiredException) {
            return "app.common.exception.authentication.account-credential-expired";
        }
        if (e instanceof DisabledException) {
            return "app.common.exception.authentication.account-disabled";
        }
        if (e instanceof LockedException) {
            return "app.common.exception.authentication.account-locked";
        }
        if (e instanceof AccountStatusException) {
            return "app.common.exception.authentication.account-inaccessible";
        }
        if (e instanceof BadCredentialsException) {
            return "app.common.exception.authentication.bad-credential";
        }
        return "app.common.exception.authentication";
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public final static class ErrorContent implements Serializable {

        private String message;

        private Object details;

        public static ErrorContent build(String message, MethodArgumentNotValidException ex) {
            return new ErrorContent(message, getFailedValidationFields(ex));
        }

        private static Map<String, String> getFailedValidationFields(
                MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }

        public static ErrorContent build(String message) {
            return new ErrorContent(message, null);
        }

        public static ErrorContent build(String message, Object details) {
            return new ErrorContent(message, details);
        }
    }
}
