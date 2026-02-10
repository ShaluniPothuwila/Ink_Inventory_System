package com.example.ink.controller;

import com.example.ink.model.Ink;
import com.example.ink.repository.InkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ink")
@CrossOrigin("*")
public class InkController {

    @Autowired
    private InkRepository repository;

    // Matches submitGRN() in HTML
    @PostMapping("/grn")
    public Ink saveInk(@RequestBody Ink newInk) {
        return repository.findByInkNumber(newInk.getInkNumber())
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + newInk.getQuantity());
                    existing.setRackNumber(newInk.getRackNumber());
                    existing.setExpiryDate(newInk.getExpiryDate());
                    return repository.save(existing);
                })
                .orElseGet(() -> repository.save(newInk));
    }

    // Matches searchInkDetails() in HTML
    @GetMapping("/search/{inkNumber}")
    public ResponseEntity<Ink> searchByNumber(@PathVariable String inkNumber) {
        return repository.findByInkNumber(inkNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Matches submitIssue() in HTML
    @PostMapping("/issue")
    public String issueInk(@RequestParam String inkNumber, @RequestParam Integer quantityToIssue) {
        return repository.findByInkNumber(inkNumber)
                .map(ink -> {
                    if (ink.getQuantity() < quantityToIssue) return "Error: Not enough stock!";
                    ink.setQuantity(ink.getQuantity() - quantityToIssue);
                    repository.save(ink);
                    return "Success! " + quantityToIssue + " units issued.";
                })
                .orElse("Error: Ink not found.");
    }

    // Matches loadAllReport() and others in HTML
    @GetMapping("/all")
    public List<Ink> getAll() {
        return repository.findAll();
    }
}