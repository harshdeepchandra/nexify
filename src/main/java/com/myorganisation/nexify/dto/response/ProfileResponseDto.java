package com.myorganisation.nexify.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ProfileResponseDto {
    private Long id;
    private String bio;
    private String avatarUrl;
    private String displayName;
    private Date dob;
}
