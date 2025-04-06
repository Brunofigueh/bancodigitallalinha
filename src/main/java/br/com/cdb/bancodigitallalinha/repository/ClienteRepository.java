package br.com.cdb.bancodigitallalinha.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitallalinha.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
  
    boolean  cpfCheck(String cpf);

    Cliente save(Cliente cliente);

    Cliente getReferenceById(UUID clienteID);

    List<Cliente> findAll();
}
