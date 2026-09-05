package br.edu.uniesp.assistencia_uniesp.repository;

import br.edu.uniesp.assistencia_uniesp.model.OrdemServico;
import br.edu.uniesp.assistencia_uniesp.model.enums.StatusOrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByStatus(StatusOrdemServico status);
    List<OrdemServico> findByTecnicoId(Long tecnicoId);
    List<OrdemServico> findByEquipamentoClienteId(Long clienteId);
}
