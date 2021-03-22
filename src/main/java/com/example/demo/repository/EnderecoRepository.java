package com.example.demo.repository;

import com.example.demo.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
}
