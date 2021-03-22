package com.example.demo.repository;

import com.example.demo.model.ColaboradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, Long> {
}
