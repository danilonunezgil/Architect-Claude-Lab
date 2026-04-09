# Architect-Claude-Lab

A lightweight Spring Boot educational project demonstrating structured data extraction using Anthropic's Claude API. This application receives unstructured text (e.g., invoices or emails) and uses Claude to extract structured information in JSON format, emphasizing prompt engineering, reliability, and clean enterprise-style architecture.

## Project Intent

Architect-Claude-Lab is designed as a learning lab to deeply understand how Claude API works in a backend system:
- **Structured Data Extraction**: Extracting predictable JSON from free-form text.
- **Prompt Engineering**: Crafting safe, deterministic prompts to avoid hallucinations.
- **Reliability & Validation**: Handling API failures, validating responses, and ensuring enterprise-grade robustness.
- **Clean Architecture**: Following layered design principles for maintainability and testability.

This is **not** a production system but a minimal, educational API focused on clarity and learning over complexity.

## Features

- **REST API Endpoint**: `POST /api/extract` for text extraction.
- **Claude Integration**: Uses Anthropic's Messages API with configurable prompts.
- **Enterprise Practices**: Layered architecture, strong typing, error handling, and configuration management.
- **Cost Awareness**: Optimized for low token usage with efficient prompts and model selection.
- **Validation**: Schema enforcement to prevent invalid or hallucinated data.

## Architecture Overview

The application follows a clean, layered architecture under `com.danno.architect_claude_lab`:

- **config/**: Configuration for Claude API (API key, timeouts).
- **controller/**: REST endpoints (`ExtractionController`).
- **service/**: Business logic (`ExtractionService` for prompt building and orchestration).
- **client/**: External API client (`ClaudeApiClient` for Anthropic calls).
- **dto/**: Data Transfer Objects (`ExtractionRequest`, `ExtractionResponse`).
- **exception/**: Custom exceptions (`ClaudeApiException`).

This separation isolates concerns, making Claude a pluggable component.

## API Usage

### Endpoint
- **URL**: `POST /api/extract`
- **Content-Type**: `application/json`
- **Request Body**:
  ```json
  {
    "text": "Invoice #12345 from ABC Corp dated 2023-10-01 for $500.00."
  }
  ```
- **Response Body** (200 OK):
  ```json
  {
    "invoiceNumber": "12345",
    "date": "2023-10-01",
    "amount": 500.00,
    "vendorName": "ABC Corp"
  }
  ```
- **Error Responses**: 400 (Bad Request), 500 (Server Error) with details.

### Example Usage
```bash
curl -X POST http://localhost:8080/api/extract \
  -H "Content-Type: application/json" \
  -d '{"text": "Invoice #12345 from ABC Corp dated 2023-10-01 for $500.00."}'
```

## Setup and Running

### Prerequisites
- Java 17+
- Maven 3.6+
- Anthropic API Key (from [Anthropic Console](https://console.anthropic.com/))

### Installation
1. Clone or download the project.
2. Set environment variable: `export ANTHROPIC_API_KEY=your_key_here`
3. Run with Maven: `mvn spring-boot:run`

The app starts on `http://localhost:8080`.

### Configuration
- API Key: Injected via `ANTHROPIC_API_KEY` env var.
- Model: Defaults to `claude-3-haiku-20240307` (cost-effective).
- Timeouts/Retries: Configurable in `ClaudeApiConfig`.

## Learning Goals

This project helps understand Claude as a system component:
- **Claude Behavior**: Probabilistic outputs requiring validation and constraints.
- **Enterprise Integration**: Treating AI like any external service (retries, fallbacks).
- **Architectural Patterns**: Layered design for AI reliability.
- **Cost Management**: Token optimization and model selection.
- **Reliability**: Defensive programming against hallucinations and failures.

Relevant for Claude Certified Architect exam concepts like prompt design, API integration, and fault tolerance.

## Technologies
- Spring Boot 4.x
- Java 17
- Anthropic Claude API
- Maven for build

## Contributing
This is an educational project. Feel free to experiment with prompts or add features like caching.

## License
None specified—educational use only.