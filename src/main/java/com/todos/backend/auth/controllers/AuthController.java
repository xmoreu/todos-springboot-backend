package com.todos.backend.auth.controllers;





import com.todos.backend.auth.dtos.AuthResponse;
import com.todos.backend.auth.dtos.LoginUserDto;
import com.todos.backend.auth.dtos.RegisterUserDto;
import com.todos.backend.auth.models.User;
import com.todos.backend.auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
  @Autowired
  private AuthService authService;
  
  @PostMapping({"auth/register"})
  public User RegisterUser(@Validated @RequestBody RegisterUserDto registerUserDto) {
    return this.authService.registerUser(registerUserDto);
  }
  
  @PostMapping({"auth/login"})
  public AuthResponse LoginUser(@Validated @RequestBody LoginUserDto loginUserDto) {
    return this.authService.loginUser(loginUserDto);
  }
  
  @GetMapping({"auth/check-status"})
  public AuthResponse CheckStatus(@AuthenticationPrincipal User user) {
    return this.authService.checkStatus(user);
  }
}
