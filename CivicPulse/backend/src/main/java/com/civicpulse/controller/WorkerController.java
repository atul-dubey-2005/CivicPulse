package com.civicpulse.controller;
import com.civicpulse.dto.ComplaintDtos.UpdateStatusRequest; import com.civicpulse.entity.*; import com.civicpulse.repository.AssignmentRepository; import com.civicpulse.service.ComplaintService; import com.civicpulse.service.CurrentUserService; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.*;
@RestController @RequestMapping("/api/worker") @PreAuthorize("hasRole('FIELD_WORKER')")
public class WorkerController {
 private final AssignmentRepository assignments; private final CurrentUserService current; private final ComplaintService complaints;
 public WorkerController(AssignmentRepository a,CurrentUserService c,ComplaintService s){assignments=a;current=c;complaints=s;}
 @GetMapping("/tasks") public List<AssignmentView> tasks(){return assignments.findByWorkerUserIdOrderByAssignedAtDesc(current.get().getUserId()).stream().map(a->new AssignmentView(a.getAssignmentId(),a.getComplaint().getComplaintId(),a.getComplaint().getTitle(),a.getComplaint().getStatus().name(),a.getComplaint().getPriority().name(),a.getComplaint().getAddress())).toList();}
 @PostMapping("/tasks/{id}/accept") public AssignmentView accept(@PathVariable UUID id){Assignment a=assignment(id);a.setAcceptedAt(Instant.now());assignments.save(a);complaints.status(a.getComplaint().getComplaintId(),new UpdateStatusRequest(ComplaintStatus.IN_PROGRESS,"Field worker accepted task"),current.get());return view(a);}
 @PostMapping("/tasks/{id}/resolve") public AssignmentView resolve(@PathVariable UUID id,@RequestBody Map<String,String> body){Assignment a=assignment(id);a.setCompletedAt(Instant.now());assignments.save(a);complaints.status(a.getComplaint().getComplaintId(),new UpdateStatusRequest(ComplaintStatus.RESOLVED,body.getOrDefault("remarks","Work completed")),current.get());return view(a);}
 private Assignment assignment(UUID id){Assignment a=assignments.findById(id).orElseThrow(()->new NoSuchElementException("Task not found"));if(!a.getWorker().getUserId().equals(current.get().getUserId()))throw new SecurityException("Task not assigned to current worker");return a;}
 private AssignmentView view(Assignment a){return new AssignmentView(a.getAssignmentId(),a.getComplaint().getComplaintId(),a.getComplaint().getTitle(),a.getComplaint().getStatus().name(),a.getComplaint().getPriority().name(),a.getComplaint().getAddress());}
 public record AssignmentView(UUID assignmentId,UUID complaintId,String title,String status,String priority,String address){}
}
