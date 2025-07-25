package br.com.freitasdev.go_judge.core.domains.usecases;


import br.com.freitasdev.go_judge.core.domains.entities.Contest;
import br.com.freitasdev.go_judge.core.repositories.ContestRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateContestUseCase {
    private ContestRepository contestRepository;

    public Contest execute(Contest contest) {
        return this.contestRepository.save(contest);
    }
}
