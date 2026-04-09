package com.danno.architect_claude_lab.dto;

import jakarta.validation.constraints.NotBlank;

public class ExtractionRequest {

    @NotBlank(message = "Text cannot be blank")
    private String text;

    // Default constructor
    public ExtractionRequest() {}

    // Constructor
    public ExtractionRequest(String text) {
        this.text = text;
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}