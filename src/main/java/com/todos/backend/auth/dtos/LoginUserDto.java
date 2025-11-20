 package com.todos.backend.auth.dtos;
 
 import jakarta.persistence.Column;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.Pattern;
 import jakarta.validation.constraints.Size;
 
 
 public class LoginUserDto
 {
   @NotBlank(message = "El nombre es obligatorio")
   @Column(unique = true, nullable = false)
   @Size(min = 3, message = "El nombre de usuario debe tener al menos 3 caracteres")
   private String name;
   @Size(min = 6, max = 8, message = "El password debe tener entre 6 y 8 caracteres")
   @Column(nullable = false)
   @NotBlank(message = "La contraseña no puede estar vacía")
   @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "La contraseña debe tener al menos una letra mayúscula y un número")
   private String password;
   
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
 }


