package br.com.freitasdev.go_judge.application.dtos;


import br.com.freitasdev.go_judge.core.domains.entities.Contest;
import br.com.freitasdev.go_judge.core.domains.entities.Exercise;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

@Getter
public class ExerciseDTO {
    @Length(min = 1, max = 100)
    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 1)
    private String description;

    @Min(0)
    @NotNull
    private Integer score;


    @NotBlank
    @UUID
    private String contestId;


    public Exercise toEntity() {
        return new Exercise(this.name, this.description, this.score);
    }
}
