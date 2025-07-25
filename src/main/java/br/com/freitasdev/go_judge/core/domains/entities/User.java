package br.com.freitasdev.go_judge.core.domains.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;


@Entity(name = "users")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User(String name, String username, String password, RoleType role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public User() {}

}
