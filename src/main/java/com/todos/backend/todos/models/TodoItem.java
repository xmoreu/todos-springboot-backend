 package com.todos.backend.todos.models;
 
 import com.todos.backend.todos.models.TodoList;
 import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
 import jakarta.persistence.JoinColumn;
 import jakarta.persistence.ManyToOne;
 import java.time.LocalDateTime;
 import org.hibernate.annotations.CreationTimestamp;
 
 
 
 
 
 
 @Entity
 public class TodoItem
 {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column(nullable = false)
   private String title;
   private String description;
   private Boolean done = Boolean.valueOf(false);
 
   
   @ManyToOne
   @JoinColumn(name = "todo_list_id")
   private TodoList todoList;
 
   
   @CreationTimestamp
   private LocalDateTime createdAt;
 
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
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
   
   public LocalDateTime getCreatedAt() {
     return this.createdAt;
   }
   
   public void setCreatedAt(LocalDateTime createdAt) {
     this.createdAt = createdAt;
   }
   
   public TodoList getTodoList() {
     return this.todoList;
   }
   
   public void setTodoList(TodoList todoList) {
     this.todoList = todoList;
   }
 }


