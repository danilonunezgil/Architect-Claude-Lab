package com.danno.architect_claude_lab.controller;

import com.danno.architect_claude_lab.dto.ExtractionRequest;
import com.danno.architect_claude_lab.dto.ExtractionResponse;
import com.danno.architect_claude_lab.service.ExtractionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExtractionController {

    private final ExtractionService extractionService;

    @Autowired
    public ExtractionController(ExtractionService extractionService) {
        this.extractionService = extractionService;
    }

    @PostMapping("/extract")
    public ResponseEntity<ExtractionResponse> extract(@Valid @RequestBody ExtractionRequest request) {
        ExtractionResponse response = extractionService.extractData(request.getText());
        return ResponseEntity.ok(response);
    }
}