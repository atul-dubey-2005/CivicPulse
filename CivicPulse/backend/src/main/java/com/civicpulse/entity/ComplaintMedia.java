package com.civicpulse.entity;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="complaint_media") @Getter @Setter @NoArgsConstructor
public class ComplaintMedia { @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID mediaId; @ManyToOne(optional=false) @JoinColumn(name="complaint_id") private Complaint complaint; @Column(nullable=false) private String mediaType; @Column(nullable=false,columnDefinition="TEXT") private String fileUrl; @ManyToOne(optional=false) @JoinColumn(name="uploaded_by") private User uploadedBy; @Column(nullable=false) private Instant uploadedAt=Instant.now(); }
