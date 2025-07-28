package br.com.freitasdev.go_judge.application.controllers;

import br.com.freitasdev.go_judge.application.dtos.ExerciseDTO;
import br.com.freitasdev.go_judge.application.dtos.ExerciseFileValidation;
import br.com.freitasdev.go_judge.application.dtos.ExerciseSubmissionDTO;
import br.com.freitasdev.go_judge.core.domains.entities.Exercise;
import br.com.freitasdev.go_judge.core.domains.entities.ExerciseSubmission;
import br.com.freitasdev.go_judge.core.domains.usecases.CreateExerciseUseCase;
import br.com.freitasdev.go_judge.core.domains.usecases.SubmitExerciseUseCase;
import br.com.freitasdev.go_judge.core.repositories.ContestRepository;
import br.com.freitasdev.go_judge.core.repositories.ExerciseRepository;
import br.com.freitasdev.go_judge.core.repositories.ExerciseSubmissionRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class ExercisesController {
    private final ExerciseRepository exerciseRepository;
    private final ContestRepository contestRepository;
    private final ExerciseSubmissionRepository exerciseSubmissionRepository;
    private final Validator validator;

    @Autowired
    public ExercisesController(ExerciseRepository exerciseRepository, ContestRepository contestRepository, ExerciseSubmissionRepository exerciseSubmissionRepository, Validator validator) {
        this.exerciseRepository = exerciseRepository;
        this.contestRepository = contestRepository;
        this.exerciseSubmissionRepository = exerciseSubmissionRepository;
        this.validator = validator;
    }


    @PostMapping("/exercise")
    public Exercise createExercise(@Valid @RequestBody ExerciseDTO exercise) {
        var useCase = new CreateExerciseUseCase(this.contestRepository, this.exerciseRepository);
        return useCase.execute(exercise.toEntity(), exercise.getContestId());
    }

    @PostMapping("/exercise/submit")
    public ExerciseSubmission submitExercise(@Valid @RequestParam("file") MultipartFile file, @Valid @RequestParam("exerciseId") String exerciseId) {
        var exerciseSubmissionDTO = new ExerciseSubmissionDTO(exerciseId, file);
        Errors errors = new BeanPropertyBindingResult(exerciseSubmissionDTO, "exerciseSubmissionDTO");
        validator.validate(exerciseSubmissionDTO, errors);

        if(errors.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            errors.getAllErrors().forEach(error -> {
                if(error.getDefaultMessage() == null) return;

                errorMessage.append(error.getDefaultMessage());

                if(errors.getAllErrors().size() >= 2 && errorMessage.indexOf("and") == -1) {
                    errorMessage.append(" and ");
                }
            });
            throw new ValidationException(errorMessage.toString());
        }

        var useCase = new SubmitExerciseUseCase(this.exerciseSubmissionRepository);

        return useCase.execute(exerciseSubmissionDTO);
    }

}
