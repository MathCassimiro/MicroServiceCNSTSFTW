package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ColaboradorModel;
import com.example.demo.repository.ColaboradorRepository;
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
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository colabRep;

    // Incluir Colaborador
    @PostMapping("colaboradores")
    public ColaboradorModel incluir(@Validated @RequestBody ColaboradorModel colaborador) {
        return this.colabRep.save(colaborador);
    }

    // Alterar Colaborador
    @PutMapping("colaboradores/{id}")
    public ResponseEntity<ColaboradorModel> alterar(@PathVariable(value = "codigo") Long colaboradorId,
                                                    @Validated @RequestBody ColaboradorModel colaboradorParametro) throws ResourceNotFoundException {

        ColaboradorModel colaborador = colabRep.findById(colaboradorId)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador " + colaboradorId + " não existente no sistema."));

        colaborador.setNome(colaboradorParametro.getNome());
        colaborador.setCpf(colaboradorParametro.getCpf());
        colaborador.setStatus(colaboradorParametro.isStatus());
        colaborador.setDataNascimento(colaboradorParametro.getDataNascimento());
        colaborador.setEndereco(colaboradorParametro.getEndereco());
        colaborador.setEmail(colaboradorParametro.getEmail());
        colaborador.setTelefone(colaboradorParametro.getTelefone());
        colaborador.setUsuario(colaboradorParametro.getUsuario());

        return ResponseEntity.ok(this.colabRep.save(colaborador));
    }

    //Excluir Colaborador
    @DeleteMapping("colaboradores/{id}")
    public Map<String, Boolean> excluir(@PathVariable(value = "codigo") Long colaboradorId)
            throws ResourceNotFoundException {

        ColaboradorModel colaborador = colabRep.findById(colaboradorId)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador " + colaboradorId + " não existente no sistema."));
        this.colabRep.deleteById(colaboradorId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Colaborador excluído", Boolean.TRUE);
        return response;
    }

    //Consulta Colaborador
    @GetMapping("colaboradores/{id}")
    public ResponseEntity<ColaboradorModel> consultar(@PathVariable(value = "codigo") Long colaboradorId)
            throws ResourceNotFoundException {
        ColaboradorModel colaborador = colabRep.findById(colaboradorId).orElseThrow(
                () -> new ResourceNotFoundException("Colaborador " + colaboradorId + " não existente no sistema."));

        return ResponseEntity.ok().body(colaborador);
    }

    //Listar Colaborador
    @GetMapping("/colaboradores")
    public List<ColaboradorModel> listar() {
        return this.colabRep.findAll();
    }

}
