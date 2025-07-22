package br.com.freitasdev.go_judge.application.dtos;

import br.com.freitasdev.go_judge.core.domains.entities.RoleType;
import br.com.freitasdev.go_judge.core.domains.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.Objects;


@Getter
@Setter
public class UserDTO {
    @Size(min = 3, max = 150)
    private String name;
    @Size(min = 3, max = 50)
    private String username;

    @Size(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    @Pattern(regexp = "^(COORDINATOR|COMPETITOR)$")
    private String role;

    public User toEntity() {
        RoleType role = RoleType.COMPETITOR;

        if(this.role.equals("COORDINATOR")) {
            role =  RoleType.ADMIN;
        }

        return new User(this.name, this.username, this.password, role);
    }
}
