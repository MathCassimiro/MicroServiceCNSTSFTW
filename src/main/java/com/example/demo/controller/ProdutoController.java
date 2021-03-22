package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProdutoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("crudWeb/v2")
public class ProdutoController {
	@Autowired
	private ProdutoRepository pedRep;

	// Incluir Produto
	@PostMapping("produtos")
	public ProdutoModel incluir(@Validated @RequestBody ProdutoModel produto) {
		return this.pedRep.save(produto);
	}

	// Alterar Produto
	@PutMapping("produtos/{id}")
	public ResponseEntity<ProdutoModel> alterar(@PathVariable(value = "codigo") Long produtoId,
												@Validated @RequestBody ProdutoModel produtoParametro) throws ResourceNotFoundException {

		ProdutoModel produto = pedRep.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException("Produto " + produtoId + " não existente no sistema."));

        produto.setCodigoBarras(produtoParametro.getCodigoBarras());
		produto.setNome(produtoParametro.getNome());
		produto.setStatus(produtoParametro.isStatus());
		produto.setDescricao(produtoParametro.getDescricao());
		produto.setCategoria(produtoParametro.getCategoria());
		produto.setMarca(produtoParametro.getMarca());
		produto.setValor(produtoParametro.getValor());
		produto.setQuantidadeEstoque(produtoParametro.getQuantidadeEstoque());

		return ResponseEntity.ok(this.pedRep.save(produto));
	}

	//Excluir Produto
	@DeleteMapping("produtos/{id}")
	public Map<String, Boolean> excluir(@PathVariable(value = "codigo") Long produtoId)
			throws ResourceNotFoundException {

		ProdutoModel produto = pedRep.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException("Produto " + produtoId + " não existente no sistema."));
		this.pedRep.deleteById(produtoId);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Produto excluído", Boolean.TRUE);
		return response;
	}

	//Consulta Produto
	@GetMapping("produtos/{id}")
	public ResponseEntity<ProdutoModel> consultar(@PathVariable(value = "codigo") Long produtoId)
			throws ResourceNotFoundException {
		ProdutoModel produto = pedRep.findById(produtoId).orElseThrow(
				() -> new ResourceNotFoundException("Produto " + produtoId + " não existente no sistema."));

		return ResponseEntity.ok().body(produto);
	}

	//Listar Produtos
	@GetMapping("/produtos")
	public List<ProdutoModel> listar() {

		return this.pedRep.findAll();

	}

}