package com.example.FakeApiClient.Controllers;

import com.example.FakeApiClient.DTOs.ErrorResponseDto;
import com.example.FakeApiClient.Exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
