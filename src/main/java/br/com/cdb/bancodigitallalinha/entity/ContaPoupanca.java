package br.com.cdb.bancodigitallalinha.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta
{

    private String senha;
	private BigDecimal taxaRendimentoAnual;
	private BigDecimal limeteCredito;
	private boolean statusCredito;
	private BigDecimal taxaUsoCredito;
	private boolean seguroViagem = false;
	

	
	public String getSenha() 
	{
		return senha;
	}
	public void setSenha(String senha) 
	{
		this.senha = senha;
	}
	
	
	public BigDecimal getTaxaRendimentoAnual() 
	{
		return taxaRendimentoAnual;
	}
	public void setTaxaRendimentoAnual(BigDecimal taxaRendimentoAnual) 
	{
		this.taxaRendimentoAnual = taxaRendimentoAnual;
	}
	
	
	public BigDecimal getLimeteCredito() 
	{
		return limeteCredito;
	}
	public void setLimeteCredito(BigDecimal limeteCredito) 
	{
		this.limeteCredito = limeteCredito;
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
