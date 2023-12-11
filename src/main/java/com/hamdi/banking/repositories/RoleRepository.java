package com.hamdi.banking.repositories;

import com.hamdi.banking.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
}
