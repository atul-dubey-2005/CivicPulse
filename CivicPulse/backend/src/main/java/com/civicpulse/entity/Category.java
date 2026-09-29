package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.util.UUID;
@Entity @Table(name="categories") @Getter @Setter @NoArgsConstructor
public class Category { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID categoryId; @Column(nullable=false,unique=true) private String name; @ManyToOne(optional=false) @JoinColumn(name="department_id") private Department department; @Column(nullable=false) private int defaultSlaHours=48; @Column(nullable=false) private boolean active=true; }
