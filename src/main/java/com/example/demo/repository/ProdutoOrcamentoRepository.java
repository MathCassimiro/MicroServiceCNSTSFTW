package com.example.demo.repository;

import com.example.demo.model.ProdutoOrcamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoOrcamentoRepository extends JpaRepository<ProdutoOrcamentoModel, Long> {
}
