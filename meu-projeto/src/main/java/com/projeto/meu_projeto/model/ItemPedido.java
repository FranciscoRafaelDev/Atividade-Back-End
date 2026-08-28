package com.projeto.meu_projeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Produto produto;
    
    private Integer quantidade;
}
