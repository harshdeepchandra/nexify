package com.myorganisation.nexify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String avatarUrl;
    private String displayName;
    private Date dob;

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
//    @JoinColumn(name = "user")
    private User user;
}
