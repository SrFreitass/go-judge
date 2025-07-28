package br.com.freitasdev.go_judge.application.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.UUID;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
public class ExerciseSubmissionDTO {
    @UUID
    private String exerciseId;

    @ExerciseFileValidation()
    private MultipartFile file;
}
