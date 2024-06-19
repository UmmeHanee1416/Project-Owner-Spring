package com.projectOwner.Project_owner.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class User extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "userId")
    private Set<Project> projects;

    public User(String username, String email, String password) {
    }
}