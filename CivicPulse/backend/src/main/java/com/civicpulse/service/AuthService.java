package com.civicpulse.service;
import com.civicpulse.dto.AuthDtos.*; import com.civicpulse.entity.*; import com.civicpulse.repository.UserRepository; import com.civicpulse.security.JwtService; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
@Service public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthService(UserRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
 public AuthResponse register(RegisterRequest r){String email=r.email().trim().toLowerCase();if(users.findByEmailIgnoreCase(email).isPresent())throw new IllegalArgumentException("Email already registered");User u=new User();u.setFullName(r.fullName().trim());u.setEmail(email);u.setPhone(r.phone());u.setPasswordHash(encoder.encode(r.password()));u.setRole(Role.CITIZEN);users.save(u);return response(u);}
 public AuthResponse login(LoginRequest r){User u=users.findByEmailIgnoreCase(r.email()).orElseThrow(()->new IllegalArgumentException("Invalid credentials"));if(!u.isActive()||!encoder.matches(r.password(),u.getPasswordHash()))throw new IllegalArgumentException("Invalid credentials");return response(u);}
 private AuthResponse response(User u){return new AuthResponse(jwt.generate(u.getUserId(),u.getEmail(),u.getRole().name()),new UserView(u.getUserId().toString(),u.getFullName(),u.getEmail(),u.getRole().name()));}
}
