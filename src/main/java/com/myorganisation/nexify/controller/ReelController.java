package com.myorganisation.nexify.controller;

import com.myorganisation.nexify.dto.request.ReelRequestDto;
import com.myorganisation.nexify.dto.response.ReelResponseDto;
import com.myorganisation.nexify.service.ReelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reels")
public class ReelController {

    @Autowired
    private ReelService reelService;

    @PostMapping
    public ResponseEntity<ReelResponseDto> postReel(@RequestBody ReelRequestDto reelRequestDto) {
        return new ResponseEntity<>(reelService.postReel(reelRequestDto), HttpStatusCode.valueOf(201));
    }
}
