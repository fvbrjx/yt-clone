package com.fvbri.simpleytclone.exception;

import com.fvbri.simpleytclone.exception.user.UserAlreadyExistException;
import com.fvbri.simpleytclone.exception.video.VideoUploadException;
import com.fvbri.simpleytclone.model.response.ApiExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {VideoUploadException.class})
    public ResponseEntity<ApiExceptionResponse> handleVideoUploadError(
            VideoUploadException exception
    ) {
        ApiExceptionResponse error = new ApiExceptionResponse(
                Arrays.asList(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleException(Exception exception) {
        ApiExceptionResponse ex = new ApiExceptionResponse(
                Arrays.asList(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> handleInvalidArgument(MethodArgumentNotValidException exception) {
        ApiExceptionResponse notFound = new ApiExceptionResponse(
                exception.getBindingResult().getFieldErrors()
                        .stream()
                        .map(error -> error.getDefaultMessage())
                        .collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(notFound,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponse> handleException(UserAlreadyExistException exception) {
        ApiExceptionResponse ex = new ApiExceptionResponse(
                Arrays.asList(exception.getMessage()),
                HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(ex,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleException(EntityNotFoundException exception) {
        ApiExceptionResponse ex = new ApiExceptionResponse(
                Arrays.asList(exception.getMessage()),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(ex,HttpStatus.NOT_FOUND);
    }

}
