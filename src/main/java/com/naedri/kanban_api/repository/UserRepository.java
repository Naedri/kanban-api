package com.naedri.kanban_api.repository;

import com.naedri.kanban_api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    // ? why/how is it working ? is it following conventionnal naming ?
    boolean existsByEmail(String email);
}
