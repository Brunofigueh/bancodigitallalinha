package br.com.cdb.bancodigitallalinha.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitallalinha.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>
{
    public void sacar(BigDecimal saldo, BigDecimal valor);
    public void depositar(BigDecimal saldo, BigDecimal valor);
    public void transferir(Conta contaOrigem, Conta contaDestino, BigDecimal valor);
    public void listarContas();
    public Conta contaFinder();
    public boolean contaCheck(long numeroConta);
    public Conta buscaConta(long numeroConta);

}
