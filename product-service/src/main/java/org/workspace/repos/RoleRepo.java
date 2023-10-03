package org.workspace.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workspace.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
