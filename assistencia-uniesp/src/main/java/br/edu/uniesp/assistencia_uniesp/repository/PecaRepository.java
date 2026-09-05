package br.edu.uniesp.assistencia_uniesp.repository;

import br.edu.uniesp.assistencia_uniesp.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PecaRepository extends JpaRepository<Peca, Long> {
    Optional<Peca> findByCodigo(String codigo);
}
