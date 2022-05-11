package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "image_models")
@Data
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] imageBytes;

    @JsonIgnore
    private Long userId;

    @JsonIgnore
    private Long postId;
}
