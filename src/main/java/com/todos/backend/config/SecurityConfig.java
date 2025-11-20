 package com.todos.backend.config;
 
 import com.todos.backend.auth.jwt.JwtAuthenticationFilter;
 import jakarta.servlet.Filter;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
 import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
 import org.springframework.security.web.SecurityFilterChain;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
 @Configuration
 public class SecurityConfig {
   private final JwtAuthenticationFilter jwtFilter;
   
   public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
     this.jwtFilter = jwtFilter;
   }
   
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http
       .csrf(csrf -> csrf.disable())
       .authorizeHttpRequests(auth -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)auth.requestMatchers(new String[] {
             
             "/auth/register", "/auth/login"
           
           })).permitAll().anyRequest()).authenticated()).addFilterBefore((Filter)this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
     
     return (SecurityFilterChain)http.build();
   }
 }


