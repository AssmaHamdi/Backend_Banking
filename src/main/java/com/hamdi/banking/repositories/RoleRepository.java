package com.hamdi.banking.repositories;

import com.hamdi.banking.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleName);

}
