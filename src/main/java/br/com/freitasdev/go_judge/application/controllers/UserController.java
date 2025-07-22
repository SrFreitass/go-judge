package br.com.freitasdev.go_judge.application.controllers;

import br.com.freitasdev.go_judge.application.dtos.UserDTO;
import br.com.freitasdev.go_judge.core.domains.entities.User;
import br.com.freitasdev.go_judge.core.domains.usecases.CreateUserUseCase;
import br.com.freitasdev.go_judge.core.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("user")
    public User user(@Valid @RequestBody UserDTO userDTO) {
        var useCase = new CreateUserUseCase(this.userRepository);
        return useCase.execute(userDTO.toEntity());
    }
}
