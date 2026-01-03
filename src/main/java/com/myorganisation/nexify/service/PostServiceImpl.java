package com.myorganisation.nexify.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.myorganisation.nexify.dto.response.GenericResponseDto;
import com.myorganisation.nexify.model.Post;
import com.myorganisation.nexify.model.User;
import com.myorganisation.nexify.repository.PostRepository;
import com.myorganisation.nexify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public GenericResponseDto uploadImagePost(MultipartFile file, String content, Long userId) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();

        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            genericResponseDto.setSuccess(false);
            genericResponseDto.setMessage("User doesn't exist.");

            return genericResponseDto;
        }

        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );
        } catch(IOException e) {
            genericResponseDto.setSuccess(false);
            genericResponseDto.setMessage("File not found");

            return genericResponseDto;
        }

        String imageUrl = uploadResult.get("secure_url").toString();
        String imageKey = uploadResult.get("public_id").toString();
        String imageMimeType = uploadResult.get("resource_type") + "/" + uploadResult.get("format");
        Long imageSize = ((Number) uploadResult.get("bytes")).longValue();

        Post post = new Post();
        post.setContent(content);
        post.setImageUrl(imageUrl);
        post.setImageKey(imageKey);
        post.setImageMimeType(imageMimeType);
        post.setImageSize(imageSize);
        post.setUser(user);

        postRepository.save(post);

        Map<String, String> postDetails = new HashMap<>();
        postDetails.put("imageUrl", imageUrl);

        genericResponseDto.setSuccess(true);
        genericResponseDto.setMessage("Post uploaded successfully.");
        genericResponseDto.setDetail(postDetails);

        return genericResponseDto;
    }
}
