package com.tra22.security.exception;

import com.tra22.security.payload.global.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    private Response<?> handleError(Throwable throwable, HttpServletRequest httpServletRequest){
        if(throwable instanceof  AccessDeniedException){
            return Response.accessDeniedHandle((AccessDeniedException) throwable, httpServletRequest);
        }else if(throwable instanceof AuthenticationException){
            return Response.authenticationHandle((AuthenticationException) throwable, httpServletRequest);
        }else if(throwable instanceof NotFoundEntityException){
            return Response.notFoundEntityResponse((NotFoundEntityException) throwable, httpServletRequest);
        }else if(throwable instanceof AppException){
            return Response.handleAppException((AppException) throwable, httpServletRequest);
        }else if(throwable instanceof Exception){
            return Response.unhandledException((Exception) throwable, httpServletRequest);
        }
        Response<?> res = Response.unhandledException(httpServletRequest);
        log.error("unhandledException ", throwable);
        log.error("unhandledException Response: {}", res);
        return res;
    }
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest httpServletRequest) {
        return handleError(ex, httpServletRequest);
    }
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleAuthenticationException(AuthenticationException ex, HttpServletRequest httpServletRequest) {
        return handleError(ex, httpServletRequest);
    }
    @ExceptionHandler(value = {NotFoundEntityException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response<?> handleNotFoundEntity(Exception exception, HttpServletRequest httpServletRequest) {
        Response<?> res =  handleError(exception, httpServletRequest);
        log.error("handleNotFoundEntity ", exception);
        log.error("handleNotFoundEntity Response: {}", res);
        return res;
    }
    @ExceptionHandler(value = {AppException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> handleAppException(Exception exception, HttpServletRequest httpServletRequest) {
        Response<?> res =  handleError(exception, httpServletRequest);
        log.error("handleAppException ", exception);
        log.error("handleAppException Response: {}", res);
        return res;
    }
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> handleException(Exception exception, HttpServletRequest httpServletRequest) {
        Response<?> res =  handleError(exception, httpServletRequest);
        log.error("handleException ", exception);
        log.error("handleException Response: {}", res);
        return res;
    }
}
