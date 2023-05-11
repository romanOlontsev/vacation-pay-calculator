package ru.neoflex.edu.calculator.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.neoflex.edu.calculator.domain.response.ApiErrorResponse;
import ru.neoflex.edu.calculator.exception.BadRequestException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handle(MethodArgumentTypeMismatchException e) {
        ApiErrorResponse exceptionResponse = getApiErrorResponse(e, "Invalid vacation start date");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            BadRequestException.class,
            ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handle(RuntimeException e) {
        ApiErrorResponse exceptionResponse = getApiErrorResponse(e, "Invalid request parameters");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private ApiErrorResponse getApiErrorResponse(Exception e, String description) {
        ApiErrorResponse exceptionResponse = ApiErrorResponse.builder()
                                                             .code("400")
                                                             .description(description)
                                                             .exceptionName(e.getClass()
                                                                             .getName())
                                                             .exceptionMessage(e.getMessage())
                                                             .build();
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            exceptionResponse.addStacktraceItem(stackTraceElement.toString());
        }
        return exceptionResponse;
    }
}
