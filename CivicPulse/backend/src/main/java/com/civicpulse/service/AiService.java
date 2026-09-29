package com.civicpulse.service;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import org.springframework.web.client.RestClient;
@Service public class AiService {
 private final RestClient client; public AiService(RestClient.Builder builder,@Value("${app.ai-url}") String url){client=builder.baseUrl(url).build();}
 public double similarity(String a,String b){var r=client.post().uri("/similarity").body(new SimilarityRequest(a,b)).retrieve().body(SimilarityResponse.class);return r==null?0.0:r.score();}
 public record SimilarityRequest(String text_a,String text_b){} public record SimilarityResponse(double score){}
}
