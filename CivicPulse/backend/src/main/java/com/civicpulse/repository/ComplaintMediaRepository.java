package com.civicpulse.repository;
import com.civicpulse.entity.ComplaintMedia; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ComplaintMediaRepository extends JpaRepository<ComplaintMedia,UUID>{List<ComplaintMedia> findByComplaintComplaintIdOrderByUploadedAtAsc(UUID complaintId);}
