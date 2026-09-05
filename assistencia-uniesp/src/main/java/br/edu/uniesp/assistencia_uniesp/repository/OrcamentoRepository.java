package br.edu.uniesp.assistencia_uniesp.repository;

import br.edu.uniesp.assistencia_uniesp.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    Optional<Orcamento> findByOrdemServicoId(Long ordemServicoId);
}
