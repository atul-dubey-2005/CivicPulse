package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="resolution_verifications") @Getter @Setter @NoArgsConstructor
public class ResolutionVerification { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID verificationId; @ManyToOne(optional=false) @JoinColumn(name="complaint_id") private Complaint complaint; @ManyToOne(optional=false) @JoinColumn(name="citizen_id") private User citizen; @Column(nullable=false) private String result; private String comment; private Instant verifiedAt=Instant.now(); }
