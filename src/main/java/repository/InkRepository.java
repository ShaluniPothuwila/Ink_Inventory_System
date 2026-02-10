package com.example.ink.repository;

import com.example.ink.model.Ink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InkRepository extends JpaRepository<Ink, Long> {
    Optional<Ink> findByInkNumber(String inkNumber);
}