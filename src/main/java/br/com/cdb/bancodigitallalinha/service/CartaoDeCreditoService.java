package br.com.cdb.bancodigitallalinha.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigitallalinha.entity.CategoriaDeClientes;
import br.com.cdb.bancodigitallalinha.entity.ContaCorrente;

@Service
public class CartaoDeCreditoService {

     // CARTÃO DE CREDITO 
    @Autowired
	CategoriaDeClientes ccs;
    @Autowired
	ContaCorrente cc;
	
	public void ccCartaoCredito(BigDecimal valor, BigDecimal limiteCredito, boolean status)
	{
		/*Cartão de credito de conta corrente. 
		 * @valor: valor da translação.
		 *@limiteCredito: limite de credito 
		 *@status: se o cartão de credito esta habilitado ou não.
		 */
		
		if (statusCredito(status))
		{
			if ( valor.compareTo(limiteCredito) <= 0)
			{
				limiteCredito.subtract(valor);
			}
			System.out.println("Limite de credito insuficiênte.. ");
		}else
		{
			System.out.println("Função credito desativada.");
		}
		
	}
	
	
	public boolean statusCredito(boolean status)
	{
		/**
		 * Checa se o cartão de credito esta ativado ou desativado pelo úsuario.
		 * por default vem ativado. 
		 * 
		 * @param: cc, instância de ContaCorrente para acesso ao getter
		 * "isSatusCredito"
		 */

		
		if(cc.isStatusCredito() == false) {
			return false;
		}
		
		return true;
	}
		
	public BigDecimal taxaUsoCretido(BigDecimal valorGastoCredito, BigDecimal limiteCredito)
	{   
        BigDecimal cotaTaxMult = new BigDecimal( 0.8);
        BigDecimal aliquotaTaxMult = new BigDecimal( 0.5);

		BigDecimal cotaTax = limiteCredito.multiply(cotaTaxMult);
		BigDecimal aliquotaTax = valorGastoCredito.multiply(aliquotaTaxMult);
		if ( valorGastoCredito.compareTo(cotaTax)  >= 0)
		{
			return aliquotaTax;
		}
		BigDecimal zeroValor = new BigDecimal(0);
		return zeroValor;
	}
	
	public boolean seguroViagem(boolean choice )
	{
		if ( choice )
		{
			cc.setSeguroViagem(choice);
			return true;
		}
		return false;
	}
	
	public boolean checkSeguroViagem()
	{
		if(cc.isSeguroViagem())
		{
			return true;
		}
		return false;
	}
	
	public BigDecimal seguroRoubo()
	{
		BigDecimal valorSeguro = new BigDecimal(5_000);
		 
		return valorSeguro;
	}
	
	
	public void faturaCartaoCredito(BigDecimal saldo, BigDecimal valorGastoCredito)
	{
		BigDecimal valorAPagar ;
		
		CategoriaDeClientes limiteCred = ccs.defineCategoria(saldo);
		BigDecimal limite = limiteCred.getLimiteCredito();
		BigDecimal taxa = taxaUsoCretido(valorGastoCredito ,limite);
		if (checkSeguroViagem())
		{
            BigDecimal taxSeguroViagem = new BigDecimal(50);
			valorAPagar = limite.add(taxa ).add(taxSeguroViagem);

		}else {
			valorAPagar = limite.add(taxa);
		}
		
	
		
		
		System.out.println("===============================================");
		System.out.println("valor atual da Fatura: "+valorAPagar+"  Vencimento: xx/xx/xxxx");
		System.out.println("Credito Disponivel: "+ (limite.subtract(valorAPagar)));
		
	}
}
