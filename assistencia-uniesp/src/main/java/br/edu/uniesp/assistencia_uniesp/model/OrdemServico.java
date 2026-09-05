package br.edu.uniesp.assistencia_uniesp.model;

import br.edu.uniesp.assistencia_uniesp.model.enums.Prioridade;
import br.edu.uniesp.assistencia_uniesp.model.enums.StatusOrdemServico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordem_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Builder.Default
    private StatusOrdemServico status = StatusOrdemServico.ABERTA;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private Prioridade prioridade = Prioridade.MEDIA;

    @Column(name = "data_abertura", nullable = false)
    @Builder.Default
    private LocalDateTime dataAbertura = LocalDateTime.now();

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "descricao_defeito", columnDefinition = "TEXT")
    private String descricaoDefeito;

    // Lado "N" do relacionamento 1:N Equipamento -> OrdemServico
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipamento_id", nullable = false)
    private Equipamento equipamento;

    // Lado "N" do relacionamento N:1 OrdemServico -> Tecnico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    // 1:N OrdemServico -> HistoricoStatus
    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<HistoricoStatus> historicoStatus = new ArrayList<>();

    // 1:0..1 OrdemServico -> Diagnostico
    @OneToOne(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Diagnostico diagnostico;

    // 1:0..1 OrdemServico -> Orcamento
    @OneToOne(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Orcamento orcamento;

    // 1:N OrdemServico -> Anexo
    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Anexo> anexos = new ArrayList<>();

    // N:N OrdemServico <-> Peca, via entidade associativa ItemOrdemServico
    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ItemOrdemServico> itens = new ArrayList<>();

    /**
     * Método utilitário para manter a lista de histórico e o status atual
     * sincronizados ao trocar o status da ordem de serviço.
     */
    public void alterarStatus(StatusOrdemServico novoStatus) {
        HistoricoStatus historico = HistoricoStatus.builder()
                .ordemServico(this)
                .statusAnterior(this.status)
                .statusNovo(novoStatus)
                .data(LocalDateTime.now())
                .build();
        this.historicoStatus.add(historico);
        this.status = novoStatus;
    }
}
