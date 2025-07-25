package br.com.freitasdev.go_judge.application.dtos;

import br.com.freitasdev.go_judge.core.domains.entities.Contest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContestDTO {
    @Size(min = 3, max = 255)
    @NotBlank
    private String name;

    @Size(min = 3, max = 1000)
    @NotBlank
    private String description;

    public Contest toEntity() {
        return new Contest(this.name, this.description);
    }
}
