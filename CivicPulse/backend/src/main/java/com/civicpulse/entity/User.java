package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.util.UUID;
@Entity @Table(name="users") @Getter @Setter @NoArgsConstructor
public class User { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID userId; @Column(nullable=false) private String fullName; @Column(nullable=false,unique=true) private String email; private String phone; @Column(nullable=false) private String passwordHash; @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role=Role.CITIZEN; @Column(nullable=false) private boolean active=true; @Column(nullable=false) private java.time.Instant createdAt=java.time.Instant.now(); }
