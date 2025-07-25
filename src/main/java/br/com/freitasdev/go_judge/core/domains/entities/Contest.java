package br.com.freitasdev.go_judge.core.domains.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "contests")
@Getter
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Contest(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Contest() {}
}
