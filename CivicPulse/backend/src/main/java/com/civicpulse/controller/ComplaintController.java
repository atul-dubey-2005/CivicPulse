package com.civicpulse.controller;
import com.civicpulse.dto.ComplaintDtos.*;import com.civicpulse.entity.*;import com.civicpulse.repository.*;import com.civicpulse.service.*;import jakarta.validation.Valid;import org.springframework.http.*;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/complaints") public class ComplaintController {private final ComplaintService service;private final CurrentUserService current;private final StatusHistoryRepository history;private final NotificationRepository notifications;private final AssignmentRepository assignments;public ComplaintController(ComplaintService s,CurrentUserService c,StatusHistoryRepository h,NotificationRepository n,AssignmentRepository a){service=s;current=c;history=h;notifications=n;assignments=a;}
@PostMapping public ResponseEntity<Complaint> create(@RequestBody @Valid CreateComplaintRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r,current.get()));}
@GetMapping("/my") public List<Complaint> mine(){return service.mine(current.get());}
@GetMapping("/all") @PreAuthorize("hasAnyRole('OFFICER','ADMIN')") public List<Complaint> all(){return service.all();}
@GetMapping("/{id}") public Complaint get(@PathVariable UUID id){return service.get(id,current.get());}
@GetMapping("/{id}/timeline") public List<StatusHistory> timeline(@PathVariable UUID id){service.get(id,current.get());return history.findByComplaintComplaintIdOrderByChangedAtAsc(id);}
@PatchMapping("/{id}/status") @PreAuthorize("hasAnyRole('OFFICER','FIELD_WORKER','ADMIN')") public Complaint status(@PathVariable UUID id,@RequestBody @Valid UpdateStatusRequest r){return service.status(id,r,current.get());}
@PostMapping("/{id}/assign") @PreAuthorize("hasAnyRole('OFFICER','ADMIN')") public Complaint assign(@PathVariable UUID id,@RequestBody @Valid AssignRequest r){return service.assign(id,r,current.get());}
@PostMapping("/{id}/verification") public ResponseEntity<Void> verify(@PathVariable UUID id,@RequestBody @Valid VerifyRequest r){service.verify(id,r,current.get());return ResponseEntity.noContent().build();}
}
