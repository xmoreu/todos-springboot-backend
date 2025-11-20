package com.todos.backend.todos.services;

import com.todos.backend.auth.models.User;
import com.todos.backend.todos.dtos.CreateTodoItemDto;
import com.todos.backend.todos.dtos.CreateTodoListDto;
import com.todos.backend.todos.models.TodoItem;
import com.todos.backend.todos.models.TodoList;
import java.util.List;

public interface TodosService {
  TodoList createTodoList(User paramUser, CreateTodoListDto paramCreateTodoListDto);
  
  TodoItem createTodoItem(CreateTodoItemDto paramCreateTodoItemDto);
  
  List<TodoList> getTodoListsFromUser(User paramUser);
  
  TodoList getTodoListById(Integer paramInteger);
  
  List<TodoItem> getTodoItemsFromTodoList(Integer paramInteger);
  
  Boolean updateTodoItemDone(Integer paramInteger);
  
  Boolean deleteTodoItem(Integer paramInteger);
  
  Boolean deleteTodoList(Integer paramInteger);
}


/* Location:              C:\Programacion 2025\Java-Spring-Boot\todos-backend\backend\target\backend-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\todos\backend\todos\services\TodosService.class
 * Java compiler version: 25 (69.0)
 * JD-Core Version:       1.1.3
 */