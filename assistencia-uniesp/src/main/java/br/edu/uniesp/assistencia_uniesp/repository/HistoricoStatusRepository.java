package br.edu.uniesp.assistencia_uniesp.repository;

import br.edu.uniesp.assistencia_uniesp.model.HistoricoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoStatusRepository extends JpaRepository<HistoricoStatus, Long> {
    List<HistoricoStatus> findByOrdemServicoIdOrderByDataAsc(Long ordemServicoId);
}
