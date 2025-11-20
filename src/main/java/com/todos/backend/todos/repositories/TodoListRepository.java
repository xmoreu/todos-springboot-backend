package com.todos.backend.todos.repositories;

import com.todos.backend.auth.models.User;
import com.todos.backend.todos.models.TodoList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
  List<TodoList> findByUser(User paramUser);
}


