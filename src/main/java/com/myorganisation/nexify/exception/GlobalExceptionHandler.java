package com.myorganisation.nexify.exception;

import com.myorganisation.nexify.dto.response.GenericResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponseDto> handleRuntimeException(RuntimeException e) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setSuccess(false);
        genericResponseDto.setMessage("An error occurred. Please try again later");
        genericResponseDto.setDetail(e.getMessage());

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GenericResponseDto> handleUserNotFoundException(UserNotFoundException e) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setSuccess(false);
        genericResponseDto.setMessage(e.getMessage());

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(404));
    }
}
