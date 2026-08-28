package com.projeto.meu_projeto.service;

import com.projeto.meu_projeto.model.Cliente;
import com.projeto.meu_projeto.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }
        cliente.setAtivo(true);
        cliente.setDataCadastro(LocalDateTime.now());
        return clienteRepository.save(cliente);
    }
}
