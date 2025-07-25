package br.com.freitasdev.go_judge.application.controllers;

import br.com.freitasdev.go_judge.application.dtos.ExerciseDTO;
import br.com.freitasdev.go_judge.core.domains.entities.Exercise;
import br.com.freitasdev.go_judge.core.domains.usecases.CreateExerciseUseCase;
import br.com.freitasdev.go_judge.core.repositories.ContestRepository;
import br.com.freitasdev.go_judge.core.repositories.ExerciseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/")
public class ExercisesController {
    private final ExerciseRepository exerciseRepository;
    private final ContestRepository contestRepository;

    @Autowired
    public ExercisesController(ExerciseRepository exerciseRepository, ContestRepository contestRepository) {
        this.exerciseRepository = exerciseRepository;
        this.contestRepository = contestRepository;
    }


    @PostMapping("/exercise")
    public Exercise createExercise(@Valid @RequestBody ExerciseDTO exercise) {
        var useCase = new CreateExerciseUseCase(this.contestRepository, this.exerciseRepository);
        return useCase.execute(exercise.toEntity(), exercise.getContestId());
    }

    @PostMapping("/exercise/submit")
    public String submitExercise(@RequestParam("file") MultipartFile file, @RequestParam("exerciseId") String exerciseId) {
        return file.getOriginalFilename() + " - " + exerciseId;
    }

}
