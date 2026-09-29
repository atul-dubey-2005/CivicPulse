package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="status_history") @Getter @Setter @NoArgsConstructor
public class StatusHistory { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID historyId; @ManyToOne(optional=false) @JoinColumn(name="complaint_id") private Complaint complaint; @Enumerated(EnumType.STRING) private ComplaintStatus oldStatus; @Enumerated(EnumType.STRING) private ComplaintStatus newStatus; @ManyToOne(optional=false) @JoinColumn(name="changed_by") private User changedBy; private String remarks; private Instant changedAt=Instant.now(); }
