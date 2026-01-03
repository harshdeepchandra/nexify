package com.myorganisation.nexify.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reels")
@Data
public class Reel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @OneToOne
    private InternalData internalData;
}
