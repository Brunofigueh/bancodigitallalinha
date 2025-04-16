package br.com.cdb.bancodigitallalinha.repository;

import java.math.BigDecimal;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitallalinha.entity.Conta;
import br.com.cdb.bancodigitallalinha.entity.ContaCorrente;
import br.com.cdb.bancodigitallalinha.entity.ContaPoupanca;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>
{
    @Query ("SELECT * FROM conta_corrente WHERE numero_conta = ?1")
    public Conta findByContaCorrente(ContaCorrente contaCorrente);
    @Query ("SELECT * FROM conta_poupanca WHERE numero_conta = ?1")  
    public Conta findByContaPoupanca(ContaPoupanca contaPoupanca);
    @Query ("SELECT * FROM conta WHERE numero_conta = ?1")
    public boolean contaCheck(long numeroConta);
    @Query ("SELECT * FROM conta WHERE numero_conta = ?1")
    public Conta buscaConta(long numeroConta);
    @Query ("SELECT * FROM conta")
    public void listarContas();

}
