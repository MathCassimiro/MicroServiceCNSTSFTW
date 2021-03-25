package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EnderecoModel;
import com.example.demo.repository.EnderecoRepository;
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
@RequestMapping("pecacerta/v2")
public class EnderecoController {

    @Autowired
    private EnderecoRepository endRep;

    // Incluir Endereco
    @PostMapping("enderecos")
    public EnderecoModel incluir(@Validated @RequestBody EnderecoModel endereco) {
        return this.endRep.save(endereco);
    }

    // Alterar Endereco
    @PutMapping("enderecos/{id}")
    public ResponseEntity<EnderecoModel> alterar(@PathVariable(value = "codigo") Long enderecoId,
                                                @Validated @RequestBody EnderecoModel enderecoParametro) throws ResourceNotFoundException {

        EnderecoModel endereco = endRep.findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço " + enderecoId + " não existente no sistema."));

        endereco.setCep(enderecoParametro.getCep());
        endereco.setLogradouro(enderecoParametro.getLogradouro());
        endereco.setComplemento(enderecoParametro.getComplemento());
        endereco.setNumero(enderecoParametro.getNumero());
        endereco.setCidade(enderecoParametro.getCidade());
        endereco.setEstado(enderecoParametro.getEstado());

        return ResponseEntity.ok(this.endRep.save(endereco));
    }

    //Excluir Endereco
    @DeleteMapping("enderecos/{id}")
    public Map<String, Boolean> excluir(@PathVariable(value = "codigo") Long enderecoId)
            throws ResourceNotFoundException {

        EnderecoModel endereco = endRep.findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço " + enderecoId + " não existente no sistema."));
        this.endRep.deleteById(enderecoId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Endereço excluído", Boolean.TRUE);
        return response;
    }

    //Consulta Endereco
    @GetMapping("enderecos/{id}")
    public ResponseEntity<EnderecoModel> consultar(@PathVariable(value = "codigo") Long enderecoId)
            throws ResourceNotFoundException {
        EnderecoModel endereco = endRep.findById(enderecoId).orElseThrow(
                () -> new ResourceNotFoundException("Endereço " + enderecoId + " não existente no sistema."));

        return ResponseEntity.ok().body(endereco);
    }

    //Listar Endereços
    @GetMapping("/enderecos")
    public List<EnderecoModel> listar() {
        return this.endRep.findAll();
    }

}
