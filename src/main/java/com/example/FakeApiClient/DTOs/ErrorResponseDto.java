package com.example.FakeApiClient.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponseDto {
    String message;
    HttpStatus status;
}
