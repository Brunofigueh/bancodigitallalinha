package br.com.cdb.bancodigitallalinha.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta 
{	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private BigDecimal saldo;

	@ManyToOne
	private Cliente cliente;

	private CategoriaDeClientes  categoria;

	@Column(unique = true)
	private long numeroConta;
	

	public BigDecimal consultaSaldo()
	{
		return saldo;
	}
	
	public CategoriaDeClientes getCategoria() {
		return categoria;
	}

	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCategoria(CategoriaDeClientes categoria) {
		this.categoria = categoria;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public long getNumeroConta() 
	{
		return numeroConta;
	}
	
	public void setNumeroConta(long ccNum) 
	{
		this.numeroConta = ccNum;
	}
	
	
	public BigDecimal getSaldo() 
	{
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) 
	{
		this.saldo = saldo;
	}
}
