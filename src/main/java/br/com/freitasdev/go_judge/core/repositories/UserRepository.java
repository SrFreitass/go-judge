package br.com.freitasdev.go_judge.core.repositories;

import br.com.freitasdev.go_judge.core.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> { }
