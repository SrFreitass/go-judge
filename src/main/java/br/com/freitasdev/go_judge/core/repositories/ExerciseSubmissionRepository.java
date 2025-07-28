package br.com.freitasdev.go_judge.core.repositories;

import br.com.freitasdev.go_judge.core.domains.entities.ExerciseSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseSubmissionRepository extends JpaRepository<ExerciseSubmission, UUID> { }
