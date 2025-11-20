package com.todos.backend.todos.repositories;

import com.todos.backend.todos.models.TodoItem;
import com.todos.backend.todos.models.TodoList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {
  List<TodoItem> findByTodoList(TodoList paramTodoList);
}


