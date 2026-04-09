package com.naedri.kanban_api.repository;

import com.naedri.kanban_api.domain.enums.RoleName;
import com.naedri.kanban_api.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleName name);
}
