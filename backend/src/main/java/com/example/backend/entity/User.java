package com.example.backend.entity;

import com.example.backend.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Version;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "text")
    private String bio;

    @Column(length = 3000)
    private String password;

    @ElementCollection(targetClass = UserRole.class) // for mapping non-entities (embeddable or basic)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UserRole> role = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Version
    private Integer version;

}
