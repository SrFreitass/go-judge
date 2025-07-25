package br.com.freitasdev.go_judge.core.domains.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "exercises")
@Getter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer score;

    @ManyToOne(cascade = CascadeType.ALL)
    @Setter
    private Contest contest;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Exercise(String name, String description, Integer score) {
        this.name = name;
        this.description = description;
        this.score = score;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Exercise() {}
}

