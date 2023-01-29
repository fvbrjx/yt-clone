package com.fvbri.simpleytclone.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiExceptionResponse {
    private final List<String> messages;
    private final HttpStatus httpStatus;
    private final Integer errorCode;
}
