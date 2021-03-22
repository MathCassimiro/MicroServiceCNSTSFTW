package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MarcaModel;
import com.example.demo.repository.MarcaRepository;
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
public class MarcaController {


    @Autowired
    private MarcaRepository marcaRep;


    // Incluir Marca
    @PostMapping("marcas")
    public MarcaModel incluir(@Validated @RequestBody MarcaModel marca) {
        return this.marcaRep.save(marca);
    }

    // Alterar Marca
    @PutMapping("marcas/{id}")
    public ResponseEntity<MarcaModel> alterar(@PathVariable(value = "codigo") Long marcaID,
                                                 @Validated @RequestBody MarcaModel marcaParametro) throws ResourceNotFoundException {

        MarcaModel marca = marcaRep.findById(marcaID)
                .orElseThrow(() -> new ResourceNotFoundException("Marca " + marcaID + " não existente no sistema."));

        marca.setNome(marcaParametro.getNome());
        marca.setStatus(marcaParametro.isStatus());

        return ResponseEntity.ok(this.marcaRep.save(marca));
    }

    //Excluir Marca
    @DeleteMapping("marcas/{id}")
    public Map<String, Boolean> excluir(@PathVariable(value = "codigo") Long marcaId)
            throws ResourceNotFoundException {

        MarcaModel marca = marcaRep.findById(marcaId)
                .orElseThrow(() -> new ResourceNotFoundException("Marca " + marcaId + " não existente no sistema."));
        this.marcaRep.deleteById(marcaId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Marca excluída", Boolean.TRUE);
        return response;
    }

    //Consulta Marca
    @GetMapping("marcas/{id}")
    public ResponseEntity<MarcaModel> consultar(@PathVariable(value = "codigo") Long marcaId)
            throws ResourceNotFoundException {
        MarcaModel marca = marcaRep.findById(marcaId).orElseThrow(
                () -> new ResourceNotFoundException("Marca " + marcaId + " não existente no sistema."));

        return ResponseEntity.ok().body(marca);
    }

    //Listar Marcas
    @GetMapping("/marcas")
    public List<MarcaModel> listar() {
        return this.marcaRep.findAll();
    }

}
