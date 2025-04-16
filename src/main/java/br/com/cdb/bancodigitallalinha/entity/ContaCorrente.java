package br.com.cdb.bancodigitallalinha.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
//@PrimaryKeyJoinColumn(name = "id")
public class ContaCorrente extends Conta
{
	

    private String senha;
	private BigDecimal taxaMensal;
	private BigDecimal limeteCredito;
	private BigDecimal limiteConta;
	private boolean statusCredito;
	private BigDecimal taxaUsoCredito;
	private boolean seguroViagem = false;
	
	
	//Constructor
	public ContaCorrente() 
	{
		
		super();
		

	}


	public BigDecimal getLimeteCredito() 
	{
		return limeteCredito;
	}
	public BigDecimal getLimiteConta() 
	{
		return limiteConta;
	}


	
	
	public void setLimeteCredito(BigDecimal limeteCredito) 
	{
		this.limeteCredito = limeteCredito;
	}
	public void setLimiteConta(BigDecimal limiteConta) 
	{
		this.limiteConta = limiteConta;
	}



	public String getSenha() 
	{
		return senha;
	}
	public void setSenha(String senha) 
	{
		this.senha = senha;
	}



	public BigDecimal getTaxaMensal() 
	{
		return taxaMensal;
	}
	public void setTaxaMensal(BigDecimal taxaMensal) 
	{
		this.taxaMensal = taxaMensal;
	}



	public boolean isStatusCredito() 
	{
		
		return statusCredito;
	}



	public void setStatusCredito(boolean statusCredito) 
	{
		this.statusCredito = statusCredito;
	}



	public BigDecimal getTaxaUsoCredito() 
	{
		return taxaUsoCredito;
	}



	public void setTaxaUsoCredito(BigDecimal taxaUsoCredito) 
	{
		this.taxaUsoCredito = taxaUsoCredito;
	}



	public boolean isSeguroViagem() 
	{
		return seguroViagem;
	}



	public void setSeguroViagem(boolean seguroViagem) 
	{
		this.seguroViagem = seguroViagem;
	}
}
