package com.civicpulse.dto;
import com.civicpulse.entity.*; import jakarta.validation.constraints.*; import java.util.*;
public final class ComplaintDtos { private ComplaintDtos(){}
 public record CreateComplaintRequest(@NotBlank String title,@NotBlank String description,@NotNull UUID categoryId,Double latitude,Double longitude,String address,Priority priority){}
 public record UpdateStatusRequest(@NotNull ComplaintStatus status,String remarks){}
 public record AssignRequest(@NotNull UUID workerId){}
 public record VerifyRequest(@NotBlank String result,String comment){}
}
