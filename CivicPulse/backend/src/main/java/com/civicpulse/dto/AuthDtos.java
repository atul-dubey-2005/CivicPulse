package com.civicpulse.dto;
import jakarta.validation.constraints.*;
public final class AuthDtos {
 private AuthDtos(){}
 public record RegisterRequest(@NotBlank @Size(max=150) String fullName,@NotBlank @Email String email,@Size(max=20) String phone,@NotBlank @Size(min=8,max=100) String password){}
 public record LoginRequest(@NotBlank @Email String email,@NotBlank String password){}
 public record UserView(String userId,String fullName,String email,String role){}
 public record AuthResponse(String token,UserView user){}
}
