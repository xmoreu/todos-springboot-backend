 package com.todos.backend.auth.dtos;
 
 import com.todos.backend.auth.models.User;
 import java.time.LocalDateTime;
 
 public class UserResponse {
   private String name;
   private Integer id;
   private LocalDateTime createdAt;
   
   public UserResponse(User user) {
     this.id = Integer.valueOf(user.getId());
     this.name = user.getName();
     this.createdAt = user.getCreatedAt();
   }
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
   public Integer getId() {
     return this.id;
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

