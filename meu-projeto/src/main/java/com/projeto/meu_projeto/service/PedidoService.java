package com.projeto.meu_projeto.service;

import com.projeto.meu_projeto.model.Cliente;
import com.projeto.meu_projeto.model.Pedido;
import com.projeto.meu_projeto.repository.ClienteRepository;
import com.projeto.meu_projeto.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final EstoqueService estoqueService;
    private final EmailService emailService;

    public PedidoService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository, 
                         EstoqueService estoqueService, EmailService emailService) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.estoqueService = estoqueService;
        this.emailService = emailService;
    }

    public Pedido criarPedido(Pedido pedido) {
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).orElseThrow();
        if (!cliente.isAtivo()) {
            throw new RuntimeException("Cliente inativo");
        }

        estoqueService.baixarEstoque(pedido.getItens());
        pedido.calcularTotal();
        pedido.setStatus("CRIADO");
        
        Pedido salvo = pedidoRepository.save(pedido);
        emailService.enviarConfirmacao(salvo);
        
        return salvo;
    }
}
