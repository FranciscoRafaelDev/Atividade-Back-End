package br.edu.uniesp.assistencia_uniesp.repository;

import br.edu.uniesp.assistencia_uniesp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
