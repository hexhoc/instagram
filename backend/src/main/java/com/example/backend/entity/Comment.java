package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long userId;

    @Column(columnDefinition = "text", nullable = false)
    private String message;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Version
    private Integer version;
}
