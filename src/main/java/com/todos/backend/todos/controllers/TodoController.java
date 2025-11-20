 package com.todos.backend.todos.controllers;
 
 import com.todos.backend.auth.models.User;
 import com.todos.backend.todos.dtos.CreateTodoItemDto;
 import com.todos.backend.todos.dtos.CreateTodoListDto;
 import com.todos.backend.todos.dtos.TodoItemDto;
 import com.todos.backend.todos.dtos.TodoListDto;
 import com.todos.backend.todos.models.TodoItem;
 import com.todos.backend.todos.models.TodoList;
 import com.todos.backend.todos.services.TodosService;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.annotation.AuthenticationPrincipal;
 import org.springframework.validation.annotation.Validated;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RestController;
 
 
 
 
 
 
 @RestController
 public class TodoController
 {
   @Autowired
   private TodosService todosService;
   
   @PostMapping({"todos/create-todo-list"})
   public TodoListDto CreateTodoList(@Validated @RequestBody CreateTodoListDto createTodoListDto, @AuthenticationPrincipal User user) {
     TodoList todoList = this.todosService.createTodoList(user, createTodoListDto);
     
     return new TodoListDto(todoList);
   }
 
   
   @PostMapping({"todos/create-todo-item"})
   public TodoItemDto CreateTodoItem(@Validated @RequestBody CreateTodoItemDto createTodoItemDto) {
     TodoItem todoItem = this.todosService.createTodoItem(createTodoItemDto);
     return new TodoItemDto(todoItem);
   }
   
   @GetMapping({"todos/get-todo-lists"})
   public List<TodoListDto> GetTodoListsByUser(@AuthenticationPrincipal User user) {
     List<TodoList> todoLists = this.todosService.getTodoListsFromUser(user);
     List<TodoListDto> todoListsDto = new ArrayList<>();
     for (TodoList todoList : todoLists) {
       todoListsDto.add(new TodoListDto(todoList));
     }
     return todoListsDto;
   }
 
 
   
   @GetMapping({"todos/get-todo-list/{todolistId}"})
   public TodoListDto GetTodoListById(@AuthenticationPrincipal User user, @PathVariable Integer todolistId) {
     TodoList todoList = this.todosService.getTodoListById(todolistId);
     return new TodoListDto(todoList);
   }
   
   @GetMapping({"todos/delete-todo-list/{todoListId}"})
   public Map<String, String> DeleteTodoList(@AuthenticationPrincipal User user, @PathVariable Integer todoListId) {
     Boolean result = this.todosService.deleteTodoList(todoListId);
     Map<String, String> response = new HashMap<>();
     response.put("message", "Lista Eliminada");
     return response;
   }
 
   
   @GetMapping({"todos/get-todo-items/{todoListId}"})
   public List<TodoItemDto> GetTodoItemsFromList(@AuthenticationPrincipal User user, @PathVariable Integer todoListId) {
     List<TodoItem> todoItems = this.todosService.getTodoItemsFromTodoList(todoListId);
     List<TodoItemDto> todoItemDtos = new ArrayList<>();
     for (TodoItem todoItem : todoItems) {
       todoItemDtos.add(new TodoItemDto(todoItem));
     }
     return todoItemDtos;
   }
 
   
   @GetMapping({"todos/update-done/{todoItemId}"})
   public Map<String, String> UpdateTodoItemDone(@AuthenticationPrincipal User user, @PathVariable Integer todoItemId) {
     Boolean result = this.todosService.updateTodoItemDone(todoItemId);
     Map<String, String> response = new HashMap<>();
     response.put("message", "Item modificado");
     return response;
   }
   
   @GetMapping({"todos/delete-todo-item/{todoItemId}"})
   public Map<String, String> DeleteTodoItem(@AuthenticationPrincipal User user, @PathVariable Integer todoItemId) {
     Boolean result = this.todosService.deleteTodoItem(todoItemId);
     Map<String, String> response = new HashMap<>();
     response.put("message", "Item Eliminado");
     return response;
   }
 }


