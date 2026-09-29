package com.civicpulse.controller;
import com.civicpulse.dto.ComplaintDtos.*; import com.civicpulse.entity.*; import com.civicpulse.repository.UserRepository; import com.civicpulse.service.ComplaintService; import com.civicpulse.service.CurrentUserService; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/officer") @PreAuthorize("hasAnyRole('OFFICER','ADMIN')")
public class OfficerController {
 private final ComplaintService complaints; private final UserRepository users; private final CurrentUserService current;
 public OfficerController(ComplaintService c,UserRepository u,CurrentUserService cur){complaints=c;users=u;current=cur;}
 @GetMapping("/complaints") public List<Complaint> queue(){return complaints.all();}
 @GetMapping("/workers") public List<UserView> workers(){return users.findAll().stream().filter(u->u.getRole()==Role.FIELD_WORKER&&u.isActive()).map(u->new UserView(u.getUserId(),u.getFullName(),u.getEmail())).toList();}
 @PostMapping("/complaints/{id}/assign") public Complaint assign(@PathVariable UUID id,@RequestBody AssignRequest r){return complaints.assign(id,r,current.get());}
 @PatchMapping("/complaints/{id}/priority") public Complaint priority(@PathVariable UUID id,@RequestBody Map<String,String> body){return complaints.setPriority(id,Priority.valueOf(body.get("priority")),current.get());}
 public record UserView(UUID userId,String fullName,String email){}
}
