 package com.todos.backend.todos.dtos;
 
 import com.todos.backend.todos.models.TodoList;
 
 public class TodoListDto {
   private Integer id;
   private String title;
   private String description;
   private Integer userId;
   
   public TodoListDto(TodoList todoList) {
     this.id = Integer.valueOf(todoList.getId());
     this.title = todoList.getTitle();
     this.description = todoList.getDescription();
     this.userId = Integer.valueOf(todoList.getUser().getId());
   }
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getTitle() {
     return this.title;
   }
   
   public void setTitle(String title) {
     this.title = title;
   }
   
   public String getDescription() {
     return this.description;
   }
   
   public void setDescription(String description) {
     this.description = description;
   }
   
   public Integer getUserId() {
     return this.userId;
   }
   
   public void setUserId(Integer userId) {
     this.userId = userId;
   }
 }


