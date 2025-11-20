 package com.todos.backend.auth.dtos;
  
  
 
 import com.todos.backend.auth.dtos.UserResponse;
 import com.todos.backend.auth.models.User;
 
 public class AuthResponse
 {
   private String token;
   private UserResponse userResponse;
   
   public AuthResponse(String token, User user) {
     this.token = token;
     
     this.userResponse = new UserResponse(user);
   }
   
   public String getToken() {
     return this.token;
   }
   
   public void setToken(String token) {
     this.token = token;
   }
   
   public UserResponse getUser() {
     return this.userResponse;
   }
   
   public void setUser(UserResponse user) {
     this.userResponse = user;
   }
 }


