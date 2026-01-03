package com.myorganisation.nexify.dto.request;

import com.myorganisation.nexify.enums.Gender;
import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String username;
    private String password;
    private Gender gender;
}
