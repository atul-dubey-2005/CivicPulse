package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="audit_logs") @Getter @Setter @NoArgsConstructor
public class AuditLog { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID auditId; @ManyToOne @JoinColumn(name="user_id") private User user; private String action; private String entityType; private UUID entityId; private String ipAddress; private Instant createdAt=Instant.now(); }
