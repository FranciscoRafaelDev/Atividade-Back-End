package com.projeto.meu_projeto.service;

import com.projeto.meu_projeto.model.Pedido;
import com.projeto.meu_projeto.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

@Service
public class CancelamentoPedidoService {
    private final PedidoRepository pedidoRepository;
    private final EstoqueService estoqueService;
    private final PagamentoService pagamentoService;
    private final EmailService emailService;

    public CancelamentoPedidoService(PedidoRepository pedidoRepository, EstoqueService estoqueService, 
                                     PagamentoService pagamentoService, EmailService emailService) {
        this.pedidoRepository = pedidoRepository;
        this.estoqueService = estoqueService;
        this.pagamentoService = pagamentoService;
        this.emailService = emailService;
    }

    @Transactional
    public void cancelar(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();

        if ("ENVIADO".equals(pedido.getStatus()) || "ENTREGUE".equals(pedido.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido já processado");
        }

        pedido.setStatus("CANCELADO");
        pedidoRepository.save(pedido);

        estoqueService.devolverEstoque(pedido.getItens());
        pagamentoService.estornar(pedido);
        emailService.enviarCancelamento(pedido);
    }
}
