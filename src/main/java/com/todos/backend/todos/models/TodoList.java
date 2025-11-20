 package com.todos.backend.todos.models;
 
 import com.todos.backend.auth.models.User;
 import com.todos.backend.todos.models.TodoItem;
 import jakarta.persistence.CascadeType;
 import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
 import jakarta.persistence.JoinColumn;
 import jakarta.persistence.ManyToOne;
 import jakarta.persistence.OneToMany;
 import java.time.LocalDateTime;
 import java.util.List;
 import org.hibernate.annotations.CreationTimestamp;
 
 
 
 
 
 
 
 
 
 
 @Entity
 public class TodoList
 {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column(nullable = false)
   private String title;
   private String description;
   @OneToMany(mappedBy = "todoList", cascade = {CascadeType.ALL}, orphanRemoval = true)
   private List<TodoItem> todoItems;
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
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
   
   public List<TodoItem> getTodoItems() {
     return this.todoItems;
   }
   
   public void setTodoItems(List<TodoItem> todoItems) {
     this.todoItems = todoItems;
   }
   
   public User getUser() {
     return this.user;
   }
   
   public void setUser(User user) {
     this.user = user;
   }
   
   public String getDescription() {
     return this.description;
   }
   
   public void setDescription(String description) {
     this.description = description;
   }
   
   public LocalDateTime getCreatedAt() {
     return this.createdAt;
   }
   
   public void setCreatedAt(LocalDateTime createdAt) {
     this.createdAt = createdAt;
   }
 }


