 package com.todos.backend.todos.dtos;
 
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.NotNull;
 import jakarta.validation.constraints.Positive;
 
 
 
 
 
 public class CreateTodoItemDto
 {
   @NotBlank(message = "El título del item es obligatorio")
   private String title;
   private String description;
   @NotNull(message = "El ID es obligatorio")
   @Positive(message = "No es un número de id válido")
   private Integer todoList_id;
   
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
   
   public Integer getTodoList_id() {
     return this.todoList_id;
   }
   
   public void setTodoList_id(Integer todoList_id) {
     this.todoList_id = todoList_id;
   }
 }


