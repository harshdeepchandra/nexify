package com.myorganisation.nexify.dto.response;

import com.myorganisation.nexify.enums.Gender;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String username;
    private Gender gender;
    private ProfileResponseDto profileResponseDto;
    private MetaDataResponseDto metaDataResponseDto;
}
