package com.example.demo.repository;

import com.example.demo.model.MarcaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<MarcaModel, Long> {
}
