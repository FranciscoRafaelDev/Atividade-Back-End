package com.projeto.meu_projeto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome obrigatorio")
    private String nome;
    
    private String email;
    private boolean ativo;
    private boolean premium;
    private LocalDateTime dataCadastro;
}
