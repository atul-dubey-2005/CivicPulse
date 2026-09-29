package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="assignments") @Getter @Setter @NoArgsConstructor
public class Assignment { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID assignmentId; @OneToOne(optional=false) @JoinColumn(name="complaint_id",unique=true) private Complaint complaint; @ManyToOne(optional=false) @JoinColumn(name="worker_id") private User worker; @ManyToOne(optional=false) @JoinColumn(name="assigned_by") private User assignedBy; private Instant assignedAt=Instant.now(); private Instant acceptedAt; private Instant completedAt; }
