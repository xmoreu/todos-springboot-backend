 package com.todos.backend.exceptions;
 import com.todos.backend.exceptions.DatabaseException;
 import com.todos.backend.exceptions.TodoException;
 import com.todos.backend.exceptions.TokenException;
 import com.todos.backend.exceptions.UserException;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.HttpStatusCode;
 import org.springframework.http.ResponseEntity;
 import org.springframework.validation.FieldError;
 import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
 
 @ControllerAdvice
 public class GlobalExceptionHandler {
   @ExceptionHandler({MethodArgumentNotValidException.class})
   public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
     FieldError fieldError = ex.getBindingResult().getFieldError();
     String message = (fieldError != null) ? fieldError.getDefaultMessage() : "Error de validación";
     
     ErrorResponse errorResponse = new ErrorResponse(message);
     return new ResponseEntity(errorResponse, (HttpStatusCode)HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler({UserException.class})
   public ResponseEntity<ErrorResponse> handleUserExists(UserException ex) {
     ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
     return new ResponseEntity(errorResponse, (HttpStatusCode)HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler({DatabaseException.class})
   public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
     ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
     return new ResponseEntity(errorResponse, (HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @ExceptionHandler({TokenException.class})
   public ResponseEntity<ErrorResponse> handleTokenException(TokenException ex) {
     ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
     return new ResponseEntity(errorResponse, (HttpStatusCode)HttpStatus.UNAUTHORIZED);
   }
   
   @ExceptionHandler({TodoException.class})
   public ResponseEntity<ErrorResponse> handleTodoException(TodoException ex) {
     ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
     return new ResponseEntity(errorResponse, (HttpStatusCode)HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler({MethodArgumentTypeMismatchException.class})
   public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
     return ResponseEntity.badRequest()
       .body(new ErrorResponse("El parámetro '" + ex.getName() + "' debe ser un número"));
   }
 }


