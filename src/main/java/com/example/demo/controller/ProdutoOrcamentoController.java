package com.example.demo.controller;

import com.example.demo.repository.ProdutoOrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("crudWeb/v2")
public class ProdutoOrcamentoController {

    @Autowired
    private ProdutoOrcamentoRepository prodRepository;

}
