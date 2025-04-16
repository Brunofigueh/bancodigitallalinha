package br.com.cdb.bancodigitallalinha.service;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigitallalinha.entity.CategoriaDeClientes;
import br.com.cdb.bancodigitallalinha.entity.Cliente;
import br.com.cdb.bancodigitallalinha.entity.Conta;
import br.com.cdb.bancodigitallalinha.entity.ContaPoupanca;
import br.com.cdb.bancodigitallalinha.repository.ClienteRepository;
import br.com.cdb.bancodigitallalinha.repository.ContaRepository;

@Service
public class ContaPopancaService 
{
    @Autowired
    ContaRepository contaDao;
    @Autowired
    ClienteRepository clienteDao;
	
	public boolean criarCPoupanca( String senha, BigDecimal saldo , String cpf)
	{   
        Cliente cliente = buscaCliente(cpf);

		if ( (cliente == null) )
		{
			return false;
		}
		if ( !validarSenha(senha) )
		{
			return false;
		}
        
		
		CategoriaDeClientes limites = CategoriaDeClientes.defineCategoria(saldo);
		
		ContaPoupanca cp = new ContaPoupanca();
		cp.setCliente(cliente);
		cp.setCategoria(limites);
		cp.setSenha(senha);
		cp.setLimeteCredito(limites.getLimiteCredito());
		cp.setTaxaRendimentoAnual(limites.getTaxAnual());
		
		long cpNum = geradorNumeroCP();
		cp.setNumeroConta(cpNum);
		
		contaDao.save(cp);
		return true;
	}
	
	
	//VALIDAÇÃO DE SENHA 
	public boolean validarSenha(String senha)
	{
		/**
		 * validação simples de senha
		 */
		if (senha ==  null || senha.length() != 8)
		{
			return false;
		}
		
		return true;
	}
	
	//SAQUE DE VALORES 
	public void sacar(BigDecimal saldo, BigDecimal valor)
	{	
		/*
		 * Método de saque em conta Poupança considerando regras de negocio. 
		 * 
		 */
		
		if (valor.compareTo(saldo) <= 0 ) {
			saldo = saldo.subtract(valor);
		}
		else
		{
			System.out.println("Saldo insuficiente...");
		}
	}
	
	

	//DOSITO EM CONTA POUPANÇA 
	
	public void depositar(BigDecimal valor, long numeroContaPattern)
	/**
	 * @param numeroContaPattern: Número da conta a recever
	 * @param valor: valor a depositar
	 * @param: saldo: saldo da conta
	 */
	{
		BigDecimal saldo = contaDao.buscaConta(numeroContaPattern).getSaldo();
		saldo.add(valor);
		System.out.println("Conta "+contaDao.buscaConta(numeroContaPattern)+ " Saldo: "+ saldo);
	}
	
	
	//TRANFERENCIAS 
	public void transfereciaPix(BigDecimal valor,  long numeroConta, long contaReceb)
    /**
     * Método de transferencia entre contas.        
     */
	{
		BigDecimal saldo = contaDao.buscaConta(numeroConta).getSaldo();
		
		
		if( contaDao.contaCheck(numeroConta) && saldo.compareTo(valor) >= 0)
		{
			
			depositar(valor, contaReceb) ;

		}else {
				System.out.println("Não foi possível fazer sua transferência."
						+ "Por favor confira o número da conta e o saldo.");
			}
	}
	

	
	
	//GERADOR DE NÉMERO DE CONTA POUPAÇA
	public long geradorNumeroCP()
	{
		/**
		 * Método ira gerar o numero da conta corrente.
		 * 
		 * @param randons: Intância de Random para geração de números aleatorios.
		 * @param contaPoupancaPrimaryDigts: Três primeiros números fixos das CP que são 302.
		 * @param contaPpSecundary: Responsavél por gerar os outros 7 números.
		 * @param numeroGerado: Número gerado para conta conrrente. 
		 * @param numeroConta: Número da conta convertido para long
		 * 
		 * @return: retorna o número conta
		 */
		Random randons = new Random();
		String numeroGerado = "";
		String numeroContaProvisorio = "";
		
		
		String contaPoupancaPrimaryDigts = "302";
		
		for (int i = 0; i < 7; i++)
		{
			int contaPpSecundary =  randons.nextInt(9);
			numeroGerado += contaPpSecundary;
		}
		
		numeroContaProvisorio = contaPoupancaPrimaryDigts +numeroGerado;
		
		long numeroConta = Long.parseUnsignedLong(numeroContaProvisorio);
		System.out.println(numeroConta);
		
		return numeroConta;
	}
	
    //Buscar Cliente por CPF
    public Cliente buscaCliente(String cpf)
    /**
     * Fara a busca do cliente na base considerando o CPF.
     * @param cpf: CPF do cliente a ser buscado.
     * @return: Retorna o cliente caso o encontre.
     * @return: Retorna null caso não encontre o cliente.   
     */
    {
      for (Cliente c: clienteDao.findAll())
      {
          if (c.getCPF().equals(cpf))
          {
              return c;
          }
      }
        return null;

    }


	//EXIBIR CONTAS POUPANÇAS 
    /**
     * Método que ira listar todas as contas poupancas
     * @param: contaDao: Instância do DAO de conta.     
     */
	public void mostraContasPoupancas() {
		 contaDao.listarContas();
		
	}

	public Conta getContasPopanca()
    /**
     * Método que ira listar todas as contas poupancas          
     */
	{
		for (Conta c: contaDao.findAll())
		{
			if (c instanceof ContaPoupanca)
			{
				return c;
			}
		}
		return null;
	}

}
