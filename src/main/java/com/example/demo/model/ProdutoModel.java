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
@Table(name = "produto")
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

    @Column(name = "codigodebarras")
    private String codigoBarras;

    @Column(name = "nome")
    private String nome;

    @Column(name = "status")
    private boolean status;

	@Column(name = "descricao")
	private String descricao;

	@ManyToOne
    @JoinColumn(name = "categoria")
    private CategoriaModel categoria;

	@ManyToOne
    @JoinColumn(name = "marca")
    private MarcaModel marca;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "quantidadeestoque")
    private int quantidadeEstoque;

    public ProdutoModel() {
    }

    public ProdutoModel(String codigoBarras, String nome, boolean status, String descricao, CategoriaModel categoria, MarcaModel marca, Double valor, int quantidadeEstoque) {
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.status = status;
        this.descricao = descricao;
        this.categoria = categoria;
        this.marca = marca;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public MarcaModel getMarca() {
        return marca;
    }

    public void setMarca(MarcaModel marca) {
        this.marca = marca;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}