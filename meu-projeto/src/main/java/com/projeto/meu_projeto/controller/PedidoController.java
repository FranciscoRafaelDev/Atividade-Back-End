package com.projeto.meu_projeto.controller;

import com.projeto.meu_projeto.service.CancelamentoPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final CancelamentoPedidoService cancelamentoService;

    public PedidoController(CancelamentoPedidoService cancelamentoService) {
        this.cancelamentoService = cancelamentoService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        cancelamentoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
