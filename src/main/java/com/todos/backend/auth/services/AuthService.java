package com.todos.backend.auth.services;

import com.todos.backend.auth.dtos.AuthResponse;
import com.todos.backend.auth.dtos.LoginUserDto;
import com.todos.backend.auth.dtos.RegisterUserDto;
import com.todos.backend.auth.models.User;

public interface AuthService {
  User registerUser(RegisterUserDto paramRegisterUserDto);
  
  AuthResponse loginUser(LoginUserDto paramLoginUserDto);
  
  AuthResponse checkStatus(User paramUser);
}


