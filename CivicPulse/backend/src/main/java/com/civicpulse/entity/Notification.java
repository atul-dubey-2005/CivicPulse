package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="notifications") @Getter @Setter @NoArgsConstructor
public class Notification { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID notificationId; @ManyToOne(optional=false) @JoinColumn(name="user_id") private User user; @ManyToOne @JoinColumn(name="complaint_id") private Complaint complaint; private String title; @Column(columnDefinition="TEXT") private String message; private boolean readStatus=false; private Instant createdAt=Instant.now(); }
