 package com.todos.backend.todos.services.impl;
 
 import com.todos.backend.auth.models.User;
 import com.todos.backend.auth.respositories.AuthRepository;
 import com.todos.backend.exceptions.DatabaseException;
 import com.todos.backend.exceptions.TodoException;
 import com.todos.backend.todos.dtos.CreateTodoItemDto;
 import com.todos.backend.todos.dtos.CreateTodoListDto;
 import com.todos.backend.todos.models.TodoItem;
 import com.todos.backend.todos.models.TodoList;
 import com.todos.backend.todos.repositories.TodoItemRepository;
 import com.todos.backend.todos.repositories.TodoListRepository;
 import com.todos.backend.todos.services.TodosService;
 import java.util.List;
 import java.util.Optional;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 @Service
 public class TodosServiceImpl
   implements TodosService
 {
   @Autowired
   private AuthRepository authRepository;
   @Autowired
   private TodoListRepository todoListRepository;
   @Autowired
   private TodoItemRepository todoItemRepository;
   
   public TodoList createTodoList(User user, CreateTodoListDto createTodoListDto) {
     TodoList todoList = new TodoList();
     todoList.setTitle(createTodoListDto.getTitle());
     todoList.setDescription(createTodoListDto.getDescription());
     todoList.setUser(user);
     
     try {
       TodoList todoListSaved = (TodoList)this.todoListRepository.save(todoList);
       
       return todoListSaved;
     } catch (Exception e) {
       throw new DatabaseException("Error al crear la lista");
     } 
   }
 
 
 
   
   public TodoItem createTodoItem(CreateTodoItemDto createTodoItemDto) {
     Optional<TodoList> todoList = this.todoListRepository.findById(createTodoItemDto.getTodoList_id());
     if (!todoList.isPresent()) {
       throw new TodoException("No existe una lista con ese id");
     }
     TodoItem todoItem = new TodoItem();
     todoItem.setTitle(createTodoItemDto.getTitle());
     todoItem.setDescription(createTodoItemDto.getDescription());
     todoItem.setTodoList(todoList.get());
     try {
       TodoItem todoItemSaved = (TodoItem)this.todoItemRepository.save(todoItem);
       return todoItemSaved;
     } catch (Exception e) {
       throw new DatabaseException("Error al crear el item");
     } 
   }
 
 
 
   
   public List<TodoList> getTodoListsFromUser(User user) {
     List<TodoList> todoLists = this.todoListRepository.findByUser(user);
     return todoLists;
   }
 
 
   
   public TodoList getTodoListById(Integer id) {
     Optional<TodoList> todoList = this.todoListRepository.findById(id);
     if (!todoList.isPresent())
       throw new TodoException("No hay ninguna lista con ese id"); 
     return todoList.get();
   }
 
 
   
   public List<TodoItem> getTodoItemsFromTodoList(Integer todoListId) {
     Optional<TodoList> todoList = this.todoListRepository.findById(todoListId);
     if (!todoList.isPresent())
       throw new TodoException("No existe ninfuna lista con ese id"); 
     List<TodoItem> todoItems = this.todoItemRepository.findByTodoList(todoList.get());
     return todoItems;
   }
 
   
   public Boolean updateTodoItemDone(Integer todoItemId) {
     Optional<TodoItem> todoItem = this.todoItemRepository.findById(todoItemId);
     if (!todoItem.isPresent())
       throw new TodoException("No existe ningún item con ese id"); 
     ((TodoItem)todoItem.get()).setDone(Boolean.valueOf(!((TodoItem)todoItem.get()).getDone().booleanValue()));
     try {
       TodoItem todoItemUpdated = (TodoItem)this.todoItemRepository.save(todoItem.get());
       return Boolean.valueOf(true);
     } catch (Exception e) {
       throw new DatabaseException("No se ha podido realizar la operación en la BD");
     } 
   }
 
 
   
   public Boolean deleteTodoItem(Integer todoItemId) {
     Optional<TodoItem> todoItem = this.todoItemRepository.findById(todoItemId);
     if (!todoItem.isPresent()) {
       throw new TodoException("No existe ningún item con ese id");
     }
     try {
       this.todoItemRepository.deleteById(Integer.valueOf(((TodoItem)todoItem.get()).getId()));
       return Boolean.valueOf(true);
     } catch (Exception e) {
       
       throw new TodoException("El id del item no existe");
     } 
   }
 
   
   public Boolean deleteTodoList(Integer todoListId) {
     Optional<TodoList> todoList = this.todoListRepository.findById(todoListId);
     if (!todoList.isPresent()) {
       throw new TodoException("No existe ninguna lista con ese id");
     }
     try {
       this.todoListRepository.deleteById(Integer.valueOf(((TodoList)todoList.get()).getId()));
       return Boolean.valueOf(true);
     } catch (Exception e) {
       
       throw new TodoException("El id de la lista no existe");
     } 
   }
 }


