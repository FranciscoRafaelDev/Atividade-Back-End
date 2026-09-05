package br.edu.uniesp.assistencia_uniesp.model;

import br.edu.uniesp.assistencia_uniesp.model.enums.StatusOrcamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orcamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_mao_obra", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal valorMaoObra = BigDecimal.ZERO;

    @Column(name = "valor_pecas", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal valorPecas = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private StatusOrcamento status = StatusOrcamento.PENDENTE;

    private LocalDate validade;

    // Lado dono do relacionamento 1:0..1 OrdemServico -> Orcamento
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ordem_servico_id", nullable = false, unique = true)
    private OrdemServico ordemServico;

    @Transient
    public BigDecimal getValorTotal() {
        return valorMaoObra.add(valorPecas);
    }
}
