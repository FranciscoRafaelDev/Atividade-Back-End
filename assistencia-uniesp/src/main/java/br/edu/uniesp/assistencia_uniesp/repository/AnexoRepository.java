package br.edu.uniesp.assistencia_uniesp.repository;

import br.edu.uniesp.assistencia_uniesp.model.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnexoRepository extends JpaRepository<Anexo, Long> {
    List<Anexo> findByOrdemServicoId(Long ordemServicoId);
}
