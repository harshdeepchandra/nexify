package com.myorganisation.nexify.controller;

import com.myorganisation.nexify.dto.response.ServerStatusResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api")
public class ApiServerController {

    private final Instant serverStartTime;
    private final String applicationName;

    public ApiServerController(@Value("${spring.application.name}") String applicationName) {
        this.serverStartTime = Instant.now();
        this.applicationName = applicationName;
    }

    @GetMapping
    public ResponseEntity<ServerStatusResponseDto> checkServerHealth() {
        ServerStatusResponseDto serverStatusResponseDTO = new ServerStatusResponseDto(serverStartTime, applicationName);
        return new ResponseEntity<>(serverStatusResponseDTO, HttpStatusCode.valueOf(200));
    }
}