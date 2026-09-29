package com.civicpulse.config;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.Map;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> bad(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("timestamp",Instant.now(),"error","BAD_REQUEST","message",e.getMessage()));}
 @ExceptionHandler(SecurityException.class) ResponseEntity<?> forbidden(SecurityException e){return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("timestamp",Instant.now(),"error","FORBIDDEN","message",e.getMessage()));}
 @ExceptionHandler(java.util.NoSuchElementException.class) ResponseEntity<?> notFound(java.util.NoSuchElementException e){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("timestamp",Instant.now(),"error","NOT_FOUND","message",e.getMessage()));}
}
