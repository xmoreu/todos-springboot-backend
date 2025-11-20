 package com.todos.backend.todos.dtos;
 
 import com.todos.backend.todos.models.TodoItem;
 
 public class TodoItemDto {
   private Integer id;
   private String title;
   private String description;
   private Boolean done;
   
   public TodoItemDto(TodoItem todoItem) {
     this.id = Integer.valueOf(todoItem.getId());
     this.title = todoItem.getTitle();
     this.description = todoItem.getDescription();
     this.done = todoItem.getDone();
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
   
   public Boolean getDone() {
     return this.done;
   }
   
   public void setDone(Boolean done) {
     this.done = done;
   }
 }


