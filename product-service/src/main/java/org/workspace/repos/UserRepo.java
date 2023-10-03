package org.workspace.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workspace.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
