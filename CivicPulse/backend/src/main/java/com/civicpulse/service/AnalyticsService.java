package com.civicpulse.service;
import com.civicpulse.entity.*; import com.civicpulse.repository.ComplaintRepository; import org.springframework.stereotype.Service; import java.time.Instant; import java.util.*;
@Service public class AnalyticsService {
 private final ComplaintRepository repo; public AnalyticsService(ComplaintRepository repo){this.repo=repo;}
 public Map<String,Object> summary(){List<Complaint> all=repo.findAll();Map<String,Object> m=new LinkedHashMap<>();m.put("total",all.size());long open=all.stream().filter(c->c.getStatus()!=ComplaintStatus.RESOLVED&&c.getStatus()!=ComplaintStatus.VERIFIED&&c.getStatus()!=ComplaintStatus.REJECTED).count();long resolved=all.stream().filter(c->c.getStatus()==ComplaintStatus.RESOLVED||c.getStatus()==ComplaintStatus.VERIFIED).count();long breached=all.stream().filter(c->c.getDueAt()!=null&&c.getDueAt().isBefore(Instant.now())&&c.getStatus()!=ComplaintStatus.VERIFIED&&c.getStatus()!=ComplaintStatus.REJECTED).count();m.put("open",open);m.put("resolved",resolved);m.put("slaBreached",breached);for(ComplaintStatus s:ComplaintStatus.values())m.put(s.name().toLowerCase(),all.stream().filter(c->c.getStatus()==s).count());return m;}
}
