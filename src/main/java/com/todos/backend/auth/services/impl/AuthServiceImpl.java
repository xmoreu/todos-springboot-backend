 package com.todos.backend.auth.services.impl;
 
 import com.todos.backend.auth.dtos.AuthResponse;
 import com.todos.backend.auth.dtos.LoginUserDto;
 import com.todos.backend.auth.dtos.RegisterUserDto;
 import com.todos.backend.auth.jwt.JwtService;
 import com.todos.backend.auth.models.User;
 import com.todos.backend.auth.respositories.AuthRepository;
 import com.todos.backend.auth.services.AuthService;
 import com.todos.backend.exceptions.DatabaseException;
 import com.todos.backend.exceptions.UserException;
 import com.todos.backend.utils.PasswordUtil;
 import java.util.Optional;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.validation.annotation.Validated;
 
 
 
 
 
 
 @Service
 public class AuthServiceImpl
   implements AuthService
 {
   @Autowired
   private AuthRepository authRepository;
   @Autowired
   private JwtService jwtService;
   
   public User registerUser(RegisterUserDto registerUserDto) {
     Optional<User> userExists = this.authRepository.findByName(registerUserDto.getName());
     if (userExists.isPresent())
       throw new UserException("Ya hay un usuario con ese nombre"); 
     User newUser = new User();
     
     newUser.setName(registerUserDto.getName());
     newUser.setPassword(PasswordUtil.hash(registerUserDto.getPassword()));
     
     try {
       User userSaved = (User)this.authRepository.save(newUser);
       
       return userSaved;
     } catch (Exception e) {
       throw new DatabaseException("No se ha podido grabar en la BD");
     } 
   }
 
 
 
   
   public AuthResponse loginUser(@Validated LoginUserDto loginUserDto) {
     Optional<User> user = this.authRepository.findByName(loginUserDto.getName());
     if (!user.isPresent())
       throw new UserException("No hay un usuario con este nombre"); 
     Boolean result = Boolean.valueOf(PasswordUtil.check(loginUserDto.getPassword(), ((User)user.get()).getPassword()));
     if (!result.booleanValue())
       throw new UserException("El password no coincide"); 
     try {
       String token = this.jwtService.generateToken(Integer.valueOf(((User)user.get()).getId()));
       AuthResponse authResponse = new AuthResponse(token, user.get());
       return authResponse;
     } catch (Exception e) {
       throw new DatabaseException("No se ha podido grabar en la BD");
     } 
   }
 
 
 
   
   public AuthResponse checkStatus(User user) {
     try {
       String token = this.jwtService.generateToken(Integer.valueOf(user.getId()));
       AuthResponse authResponse = new AuthResponse(token, user);
       return authResponse;
     } catch (Exception e) {
       throw new DatabaseException("No se ha podido grabar en la BD");
     } 
   }
 }


