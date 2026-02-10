package com.example.ink.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Ink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String inkNumber;
    private Integer quantity;
    private String rackNumber;
    private LocalDate expiryDate;

    // Manual Getters and Setters (The "Connectors")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInkNumber() { return inkNumber; }
    public void setInkNumber(String inkNumber) { this.inkNumber = inkNumber; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getRackNumber() { return rackNumber; }
    public void setRackNumber(String rackNumber) { this.rackNumber = rackNumber; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
}