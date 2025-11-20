 package com.todos.backend.auth.models;
 
 import com.todos.backend.todos.models.TodoList;
 import jakarta.persistence.CascadeType;
 import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
 import jakarta.persistence.OneToMany;
 import java.time.LocalDateTime;
 import java.util.List;
 import org.hibernate.annotations.CreationTimestamp;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Entity
 public class User
 {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(unique = true, nullable = false)
   private String name;
   @Column(nullable = false)
   private String password;
   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
   private List<TodoList> todoLists;
   @CreationTimestamp
   private LocalDateTime createdAt;
   
   public int getId() {
     return this.id.intValue();
   }
   
   public void setId(int id) {
     this.id = Integer.valueOf(id);
   }
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = name;
   }
 
 
   
   public String getPassword() {
     return this.password;
   }
   
   public void setPassword(String password) {
     this.password = password;
   }
   
   public List<TodoList> getTodoLists() {
     return this.todoLists;
   }
   
   public void setTodoLists(List<TodoList> todoLists) {
     this.todoLists = todoLists;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public LocalDateTime getCreatedAt() {
     return this.createdAt;
   }
   
   public void setCreatedAt(LocalDateTime createdAt) {
     this.createdAt = createdAt;
   }
 }


