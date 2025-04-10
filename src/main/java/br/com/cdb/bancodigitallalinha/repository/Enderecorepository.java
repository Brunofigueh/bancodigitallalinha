package br.com.cdb.bancodigitallalinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitallalinha.entity.Endereco;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    // você pode adicionar métodos como:
    // List<Endereco> findByCidade(String cidade);
}
