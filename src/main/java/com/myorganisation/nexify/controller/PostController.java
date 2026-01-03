package com.myorganisation.nexify.controller;

import com.myorganisation.nexify.dto.response.GenericResponseDto;
import com.myorganisation.nexify.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/upload/{userId}")
    public ResponseEntity<GenericResponseDto> uploadPost(@RequestParam MultipartFile file, @RequestParam String content, @PathVariable Long userId) throws IOException {
        return new ResponseEntity<>(postService.uploadImagePost(file, content, userId), HttpStatusCode.valueOf(201));
    }
}
