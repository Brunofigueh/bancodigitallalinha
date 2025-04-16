package br.com.cdb.bancodigitallalinha.repository;

import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitallalinha.entity.Conta;
import br.com.cdb.bancodigitallalinha.entity.ContaCorrente;
import br.com.cdb.bancodigitallalinha.entity.ContaPoupanca;

@Repository
public interface ContaCorrenteRepository extends JpaRepository <ContaCorrente, UUID>{

    public ContaCorrente findByNumeroConta(long numeroConta);
  
}
