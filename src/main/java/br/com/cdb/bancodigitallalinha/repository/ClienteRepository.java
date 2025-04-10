package br.com.cdb.bancodigitallalinha.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitallalinha.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
  


    // Cliente save(Cliente cliente);

    // Cliente getReferenceById(UUID clienteID);

    // List<Cliente> findAll();

    Optional<Cliente> findByCpf(String cpf);

}
