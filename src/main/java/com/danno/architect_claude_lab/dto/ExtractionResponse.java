package com.danno.architect_claude_lab.dto;

import java.math.BigDecimal;

public class ExtractionResponse {

    private String invoiceNumber;
    private String date;
    private BigDecimal amount;
    private String vendorName;

    // Default constructor
    public ExtractionResponse() {}

    // Constructor
    public ExtractionResponse(String invoiceNumber, String date, BigDecimal amount, String vendorName) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.amount = amount;
        this.vendorName = vendorName;
    }

    // Getters and setters
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}