package com.projeto.meu_projeto.controller;

import com.projeto.meu_projeto.model.Cliente;
import com.projeto.meu_projeto.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente) {
        Cliente salvo = clienteService.cadastrar(cliente);
        return ResponseEntity.ok(salvo);
    }
}
