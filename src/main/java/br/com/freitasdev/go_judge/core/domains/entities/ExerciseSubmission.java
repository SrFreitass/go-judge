package br.com.freitasdev.go_judge.core.domains.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "exercise_submissions")
@Getter
public class ExerciseSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String filepath;

    private Integer score;

    private String status;

    @Null
    @Setter
    private Timestamp ratedAt;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;

    public ExerciseSubmission(String filepath, Integer score, String status) {
        this.filepath = filepath;
        this.score = score;
        this.status = status;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public ExerciseSubmission() {}
}
