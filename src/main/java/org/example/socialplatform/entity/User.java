package org.example.socialplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.socialplatform.entity.Post;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false, length= 50)
    private String username;
    @Column(nullable = false, length=100)
    private String email;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Post> posts = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )

    @JsonIgnore
    private Set<User> followers = new HashSet<>();
    @ManyToMany (mappedBy = "followers")
    @JsonIgnore
    private Set<User> following = new HashSet<>();

    @Column (nullable = false, updatable = false)
    private LocalDateTime CreatedAt;

    @Column (nullable = false)
    private LocalDateTime UpdatedAt;

    @Column(nullable = false)
    private String password;



    @PrePersist
    protected void onCreate(){
        this.CreatedAt= LocalDateTime.now();
        this.UpdatedAt= LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.UpdatedAt = LocalDateTime.now();
    }
}

