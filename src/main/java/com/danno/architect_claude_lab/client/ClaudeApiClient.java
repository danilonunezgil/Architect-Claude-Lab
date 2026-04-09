package com.danno.architect_claude_lab.client;

import com.danno.architect_claude_lab.exception.ClaudeApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;
import java.util.Map;

@Component
public class ClaudeApiClient {

    private final WebClient webClient;

    @Autowired
    public ClaudeApiClient(WebClient claudeWebClient) {
        this.webClient = claudeWebClient;
    }

    public String callClaude(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "model", "claude-3-haiku-20240307",
                "max_tokens", 1000,
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );

        try {
            return webClient.post()
                    .uri("/v1/messages")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientException e) {
            throw new ClaudeApiException("Failed to call Claude API", e);
        }
    }
}