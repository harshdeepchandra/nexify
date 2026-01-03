package com.myorganisation.nexify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interestTags;

    @JsonIgnore
    @OneToOne(mappedBy = "metaData")
//    @JoinColumn(name = "user")
    private User user;
}
