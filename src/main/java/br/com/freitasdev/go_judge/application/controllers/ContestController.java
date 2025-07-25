package br.com.freitasdev.go_judge.application.controllers;


import br.com.freitasdev.go_judge.application.dtos.ContestDTO;
import br.com.freitasdev.go_judge.core.domains.entities.Contest;
import br.com.freitasdev.go_judge.core.domains.usecases.CreateContestUseCase;
import br.com.freitasdev.go_judge.core.repositories.ContestRepository;
import br.com.freitasdev.go_judge.core.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1")
public class ContestController {
    private final ContestRepository contestRepository;

    @Autowired
    public ContestController(ContestRepository contestRepository) { this.contestRepository = contestRepository; }

    @PostMapping("/contest")
    public Contest createContest(@Valid @RequestBody ContestDTO contestDTO) {
        var useCase = new CreateContestUseCase(this.contestRepository);
        return useCase.execute(contestDTO.toEntity());
    }
}
