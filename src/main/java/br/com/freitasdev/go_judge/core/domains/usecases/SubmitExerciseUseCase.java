package br.com.freitasdev.go_judge.core.domains.usecases;

import br.com.freitasdev.go_judge.application.dtos.ExerciseSubmissionDTO;
import br.com.freitasdev.go_judge.core.domains.entities.ExerciseSubmission;
import br.com.freitasdev.go_judge.core.repositories.ExerciseSubmissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.UUID;

public class SubmitExerciseUseCase {
    private ExerciseSubmissionRepository exerciseSubmissionRepository;

    public SubmitExerciseUseCase(ExerciseSubmissionRepository exerciseSubmissionRepository) {
        this.exerciseSubmissionRepository = exerciseSubmissionRepository;
    }

    public ExerciseSubmission execute(ExerciseSubmissionDTO exerciseSubmissionDTO) {
        // TODO
        Path path = Paths.get("C:\\Users\\freitas\\Desktop\\Projects\\go-judge\\public\\" + exerciseSubmissionDTO.getExerciseId());


        try {
            if (!Files.exists(path)) {
                File newDirectory = new File(path.toString());

                if (!newDirectory.mkdir()) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save file");
                }
            }

            String fileName =  UUID.randomUUID() + "-" + exerciseSubmissionDTO.getFile().getOriginalFilename();

            Files.copy(exerciseSubmissionDTO.getFile().getInputStream(), Paths.get(path + "/" + fileName));

            return null;
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save file");
        }
    }
}
