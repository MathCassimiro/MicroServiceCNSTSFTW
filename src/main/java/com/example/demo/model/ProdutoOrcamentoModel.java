package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtoorcamento")
public class ProdutoOrcamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @ManyToOne
    @JoinColumn(name = "produto")
    private ProdutoModel produto;

    @Column(name="quantidade")
    private Integer quantidade;

    @Column(name = "preco")
    private Double precoProduto;

}
