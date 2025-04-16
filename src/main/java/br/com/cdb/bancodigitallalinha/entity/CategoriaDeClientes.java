package br.com.cdb.bancodigitallalinha.entity;

import java.math.BigDecimal;



public enum CategoriaDeClientes {

	COMUM("12.00", "0.5", "300", "500"),
	SUPER("8.00", "0.7", "1500", "2000"),
	PREMIUN("8.00", "0.9", "3000", "5000");

	private final BigDecimal taxAnual;
	private final BigDecimal taxManutencao;
	private final BigDecimal limiteContaCC;
	private final BigDecimal limiteCredito;

	CategoriaDeClientes(String taxAnual, String taxManutencao, String limiteContaCC, String limiteCredito) 
	{
		this.taxAnual = new BigDecimal(taxAnual);
		this.taxManutencao = new BigDecimal(taxManutencao);
		this.limiteContaCC = new BigDecimal(limiteContaCC);
		this.limiteCredito = new BigDecimal(limiteCredito);
	}

	public BigDecimal getLimiteContaCC() 
	{
		return limiteContaCC;
	}

	public BigDecimal getLimiteCredito() 
	{
		return limiteCredito;
	}

	public BigDecimal getTaxAnual() 
	{
		return taxAnual;
	}

	public BigDecimal getTaxManutencao() 
	{
		return taxManutencao;
	}

	public static CategoriaDeClientes defineCategoria(BigDecimal saldo) 
	{
		/**
		 * Define categoria de clientes conforme o saldo em conta.
		 * 
		 * @param comum:   clientes comuns com saldo <=1_000
		 * @param super:   Clientes super com saldo <=5_000
		 * @param premium: Cliente premium com saldo superior a 10_000
		 */

		BigDecimal flag_limit1 = new BigDecimal(4999);
		BigDecimal flag_limit2 = new BigDecimal(5000);

		if (saldo.compareTo(flag_limit1) < 0)
		{
			return COMUM;
		
			
		}
		if (saldo.compareTo(flag_limit2) <= 0) 
		{
			return SUPER;

		}
		else 
		{
			return PREMIUN;
		}
	}
} 