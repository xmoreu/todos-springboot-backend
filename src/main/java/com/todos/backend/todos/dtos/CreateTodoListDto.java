 package com.todos.backend.todos.dtos;
 
 import jakarta.validation.constraints.NotBlank;
 
 
 
 
 
 
 public class CreateTodoListDto
 {
   @NotBlank(message = "El título de la lista es obligatorio")
   private String title;
   private String description;
   
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


 }