package com.sm.socialmedia.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "posts")
public class Post extends AbstractAuditableEntity implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "uuid DEFAULT gen_random_uuid()", updatable = false, nullable = false)
    private UUID id;

    @ToString.Include
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ToString.Include
    @Column(name = "user_id")
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "group_id", insertable=false, updatable=false)
    private Group group;

    @ToString.Include
    @Column(name = "group_id")
    private UUID groupId;

    @Version
    @Column(name = "version")
    private Integer version;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<PostUserLike> userLikes = new LinkedHashSet<>();

    @OneToOne(mappedBy = "post", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private PostAnalytic postAnalytic;
}
