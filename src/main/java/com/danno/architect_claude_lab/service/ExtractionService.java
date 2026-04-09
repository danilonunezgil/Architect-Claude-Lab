package com.danno.architect_claude_lab.service;

import com.danno.architect_claude_lab.client.ClaudeApiClient;
import com.danno.architect_claude_lab.dto.ExtractionResponse;
import com.danno.architect_claude_lab.exception.ClaudeApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractionService {

    private final ClaudeApiClient claudeClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public ExtractionService(ClaudeApiClient claudeClient, ObjectMapper objectMapper) {
        this.claudeClient = claudeClient;
        this.objectMapper = objectMapper;
    }

    public ExtractionResponse extractData(String text) {
        String prompt = buildPrompt(text);
        String rawResponse = claudeClient.callClaude(prompt);

        try {
            // Parse Claude's response
            JsonNode root = objectMapper.readTree(rawResponse);
            String content = root.path("content").get(0).path("text").asText();

            // Deserialize into DTO
            ExtractionResponse response = objectMapper.readValue(content, ExtractionResponse.class);

            // Basic validation
            if (response.getInvoiceNumber() == null && response.getDate() == null &&
                response.getAmount() == null && response.getVendorName() == null) {
                throw new ClaudeApiException("Invalid extraction: all fields are null");
            }

            return response;
        } catch (JsonProcessingException e) {
            throw new ClaudeApiException("Failed to parse Claude response", e);
        }
    }

    private String buildPrompt(String text) {
        return """
                You are an expert data extraction assistant. Your task is to extract structured information from the provided text, which contains an invoice or similar document.

                Instructions:
                - Extract only the following fields: invoiceNumber, date, amount, vendorName.
                - If a field is not present in the text, set it to null. Do not invent or guess values.
                - Return the result as valid JSON only, with no additional text or explanations.
                - Dates should be in ISO format (YYYY-MM-DD) if available.
                - Amounts should be numeric values (e.g., 500.00) without currency symbols.

                Example Input: "Invoice #12345 from ABC Corp dated 2023-10-01 for $500.00."
                Example Output: {"invoiceNumber": "12345", "date": "2023-10-01", "amount": 500.00, "vendorName": "ABC Corp"}

                Now, extract from this text: %s
                """.formatted(text);
    }
}