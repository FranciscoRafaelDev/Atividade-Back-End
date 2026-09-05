package br.edu.uniesp.assistencia_uniesp.repository;

import br.edu.uniesp.assistencia_uniesp.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
    Optional<Diagnostico> findByOrdemServicoId(Long ordemServicoId);
}
