package com.projeto.meu_projeto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Cliente cliente;
    
    @OneToMany
    private List<ItemPedido> itens;
    
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal frete = BigDecimal.ZERO;
    private String status;

    public void calcularTotal() {
        this.total = itens.stream()
                .map(item -> item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
