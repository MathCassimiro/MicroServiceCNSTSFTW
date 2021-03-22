package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CategoriaModel;
import com.example.demo.repository.CategoriaRepository;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("crudWeb/v2")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRep;


    // Incluir Categoria
    @PostMapping("categorias")
    public CategoriaModel incluir(@Validated @RequestBody CategoriaModel categoria) {
        return this.categoriaRep.save(categoria);
    }

    // Alterar Categoria
    @PutMapping("categorias/{id}")
    public ResponseEntity<CategoriaModel> alterar(@PathVariable(value = "codigo") Long categoriaId,
                                              @Validated @RequestBody CategoriaModel categoriaParametro) throws ResourceNotFoundException {

        CategoriaModel categoria = categoriaRep.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("categoria " + categoriaId + " não existente no sistema."));

        categoria.setNome(categoriaParametro.getNome());
        categoria.setStatus(categoriaParametro.isStatus());

        return ResponseEntity.ok(this.categoriaRep.save(categoria));
    }

    //Excluir Categoria
    @DeleteMapping("categorias/{id}")
    public Map<String, Boolean> excluir(@PathVariable(value = "codigo") Long categoriaId)
            throws ResourceNotFoundException {

        CategoriaModel categoria = categoriaRep.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria " + categoriaId + " não existente no sistema."));
        this.categoriaRep.deleteById(categoriaId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Categoria excluída", Boolean.TRUE);
        return response;
    }

    //Consulta Categoria
    @GetMapping("categorias/{id}")
    public ResponseEntity<CategoriaModel> consultar(@PathVariable(value = "codigo") Long categoriaId)
            throws ResourceNotFoundException {
        CategoriaModel categoria = categoriaRep.findById(categoriaId).orElseThrow(
                () -> new ResourceNotFoundException("Categoria " + categoriaId + " não existente no sistema."));

        return ResponseEntity.ok().body(categoria);
    }

    //Listar Categoria
    @GetMapping("/categorias")
    public List<CategoriaModel> listar() {
        return this.categoriaRep.findAll();
    }

}
