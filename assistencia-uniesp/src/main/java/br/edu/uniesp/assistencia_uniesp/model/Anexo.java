package br.edu.uniesp.assistencia_uniesp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Table(name = "anexo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(length = 100)
    private String tipo;

    private Long tamanho;

    // chave/caminho do arquivo no storage (S3, disco, etc.)
    @Column(name = "storage_key", nullable = false, length = 300)
    private String storageKey;

    @Column(name = "data_upload", nullable = false)
    @Builder.Default
    private LocalDateTime dataUpload = LocalDateTime.now();

    // Lado "N" do relacionamento 1:N OrdemServico -> Anexo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ordem_servico_id", nullable = false)
    private OrdemServico ordemServico;
}
