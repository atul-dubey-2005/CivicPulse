package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.util.UUID;
@Entity @Table(name="departments") @Getter @Setter @NoArgsConstructor
public class Department { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID departmentId; @Column(nullable=false,unique=true) private String name; private String description; @Column(nullable=false) private boolean active=true; }
