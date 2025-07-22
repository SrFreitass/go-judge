package br.com.freitasdev.go_judge.core.domains.usecases;

import br.com.freitasdev.go_judge.application.dtos.UserDTO;
import br.com.freitasdev.go_judge.core.domains.entities.User;
import br.com.freitasdev.go_judge.core.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@AllArgsConstructor
public class CreateUserUseCase {
    private UserRepository userRepository;

    public User execute(User user) {
        return this.userRepository.save(user);
    }
}
