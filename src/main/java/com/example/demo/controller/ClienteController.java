package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ClienteModel;
import com.example.demo.repository.ClienteRepository;
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
public class ClienteController {
    @Autowired
    private ClienteRepository cliRep;

    // Incluir Cliente
    @PostMapping("clientes")
    public ClienteModel incluir(@Validated @RequestBody ClienteModel cliente) {
        return this.cliRep.save(cliente);
    }

    // Alterar Cliente
    @PutMapping("clientes/{id}")
    public ResponseEntity<ClienteModel> alterar(@PathVariable(value = "codigo") Long clienteId,
                                                @Validated @RequestBody ClienteModel clienteParametro) throws ResourceNotFoundException {

        ClienteModel cliente = cliRep.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente " + clienteId + " não existente no sistema."));

        cliente.setNome(clienteParametro.getNome());
        cliente.setCpfCnpj(clienteParametro.getCpfCnpj());
        cliente.setStatus(clienteParametro.isStatus());
        cliente.setEndereco(clienteParametro.getEndereco());
        cliente.setEmail(clienteParametro.getEmail());
        cliente.setTelefone(clienteParametro.getTelefone());
        cliente.setTipoCliente(clienteParametro.getTipoCliente());

        return ResponseEntity.ok(this.cliRep.save(cliente));
    }

    //Excluir Cliente
    @DeleteMapping("clientes/{id}")
    public Map<String, Boolean> excluir(@PathVariable(value = "codigo") Long clienteId)
            throws ResourceNotFoundException {

        ClienteModel cliente = cliRep.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente " + clienteId + " não existente no sistema."));
        this.cliRep.deleteById(clienteId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Cliente excluído", Boolean.TRUE);
        return response;
    }

    //Consulta Cliente
    @GetMapping("clientes/{id}")
    public ResponseEntity<ClienteModel> consultar(@PathVariable(value = "codigo") Long clienteId)
            throws ResourceNotFoundException {
        ClienteModel cliente = cliRep.findById(clienteId).orElseThrow(
                () -> new ResourceNotFoundException("Cliente " + clienteId + " não existente no sistema."));

        return ResponseEntity.ok().body(cliente);
    }

    //Listar Clientes
    @GetMapping("/clientes")
    public List<ClienteModel> listar() {

        return this.cliRep.findAll();

    }

}
