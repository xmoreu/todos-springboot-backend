 package com.todos.backend.auth.jwt;
 
 import com.todos.backend.auth.jwt.JwtService;
 import com.todos.backend.auth.models.User;
 import com.todos.backend.auth.respositories.AuthRepository;
 import jakarta.servlet.FilterChain;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.ServletRequest;
 import jakarta.servlet.ServletResponse;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import java.io.IOException;
 import java.util.Optional;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
 import org.springframework.stereotype.Component;
 import org.springframework.util.StringUtils;
 import org.springframework.web.filter.OncePerRequestFilter;
 
 @Component
 public class JwtAuthenticationFilter
   extends OncePerRequestFilter
 {
   private final JwtService jwtService;
   @Autowired
   private AuthRepository authRepository;
   
   public JwtAuthenticationFilter(JwtService jwtService) {
     this.jwtService = jwtService;
   }
   
   private void writeJsonError(HttpServletResponse response, String message, int status) throws IOException {
     response.setStatus(status);
     response.setContentType("application/json");
     response.getWriter().write("{\"message\":\"" + message + "\"}");
   }
 
 
 
 
 
   
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     try {
       String authHeader = request.getHeader("Authorization");
       if (authHeader == null) {
         writeJsonError(response, "Falta el token", 401);
         return;
       } 
       String token = null;
       
       if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
         token = authHeader.substring(7);
       } else {
         writeJsonError(response, "No es un bearer token", 401);
         
         return;
       } 
       if (token != null) {
         if (!this.jwtService.validateToken(token)) {
           writeJsonError(response, "Token inválido o expirado", 401);
           
           return;
         } 
         String idString = this.jwtService.extractUserId(token);
         Integer id = Integer.valueOf(Integer.parseInt(idString));
         
         Optional<User> userOpt = this.authRepository.findById(id);
         if (!userOpt.isPresent()) {
           writeJsonError(response, "El usuario no existe en el token", 401);
           
           return;
         } 
         User user = userOpt.get();
         UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, null);
         
         authToken.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
         SecurityContextHolder.getContext().setAuthentication((Authentication)authToken);
       
       }
     
     }
     catch (Exception e) {
       
       response.setStatus(401);
       response.getWriter().write("Error al autenticar el token");
       
       return;
     } 
     filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
   }
 
 
   
   protected boolean shouldNotFilter(HttpServletRequest request) {
     String path = request.getRequestURI();
     return (path.equals("/api/auth/register") || path.equals("/api/auth/login"));
   }
 }


