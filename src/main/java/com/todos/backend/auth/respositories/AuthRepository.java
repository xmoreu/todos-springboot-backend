package com.todos.backend.auth.respositories;

import com.todos.backend.auth.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Integer> {
  Optional<User> findByName(String paramString);
}


