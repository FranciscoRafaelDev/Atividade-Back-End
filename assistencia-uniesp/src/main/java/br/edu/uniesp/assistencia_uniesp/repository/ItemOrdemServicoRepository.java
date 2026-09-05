package br.edu.uniesp.assistencia_uniesp.repository;

import br.edu.uniesp.assistencia_uniesp.model.ItemOrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrdemServicoRepository extends JpaRepository<ItemOrdemServico, Long> {
    List<ItemOrdemServico> findByOrdemServicoId(Long ordemServicoId);
}
