package br.com.freitasdev.go_judge.core.domains.usecases;

import br.com.freitasdev.go_judge.core.domains.entities.Contest;
import br.com.freitasdev.go_judge.core.domains.entities.Exercise;
import br.com.freitasdev.go_judge.core.repositories.ContestRepository;
import br.com.freitasdev.go_judge.core.repositories.ExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

public class CreateExerciseUseCase {
    private final ContestRepository contestRepository;
    private final ExerciseRepository exerciseRepository;

    public CreateExerciseUseCase(ContestRepository contestRepository, ExerciseRepository exerciseRepository) {
        this.contestRepository = contestRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise execute(Exercise exercise, UUID contestId) {
       Optional<Contest> contest = this.contestRepository.findById(contestId);

       if(contest.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contest not found");
       }

       return this.exerciseRepository.save(exercise);
    }
}
