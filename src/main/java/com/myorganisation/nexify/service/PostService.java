package com.myorganisation.nexify.service;

import com.myorganisation.nexify.dto.response.GenericResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    GenericResponseDto uploadImagePost(MultipartFile file, String content, Long userId);
}
