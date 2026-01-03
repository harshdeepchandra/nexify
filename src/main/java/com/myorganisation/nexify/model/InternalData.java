package com.myorganisation.nexify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "internal_data")
@Data
public class InternalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    @JsonIgnore
    @OneToOne
    private Reel reel;
}
