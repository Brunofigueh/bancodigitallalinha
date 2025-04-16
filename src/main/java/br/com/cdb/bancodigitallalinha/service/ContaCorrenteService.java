package br.com.cdb.bancodigitallalinha.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cdb.bancodigitallalinha.entity.CategoriaDeClientes;
import br.com.cdb.bancodigitallalinha.entity.Cliente;
import br.com.cdb.bancodigitallalinha.entity.Conta;
import br.com.cdb.bancodigitallalinha.entity.ContaCorrente;
import br.com.cdb.bancodigitallalinha.entity.ContaPoupanca;
import br.com.cdb.bancodigitallalinha.repository.ClienteRepository;
import br.com.cdb.bancodigitallalinha.repository.ContaCorrenteRepository;

public class ContaCorrenteService {
    
    @Autowired
    ContaCorrenteRepository contaCorrenteRepository;
    @Autowired
    ClienteRepository clienteDao;

	
	public ContaCorrente criarCCorrente( String senha, BigDecimal saldo, String cpf) 
	{
		/**
		 * Criação de conta corrente e aplicação de regras de negocio. 
		 */
		
        Cliente cliente = buscaCliente(cpf);
		
		if ( (cliente == null) )
		{
			return null;
		}
		if ( !validarSenha(senha) )
		{
			return null;
		}

		
		
	
		ContaCorrente cc = new ContaCorrente();
		cc.setCliente(cliente);
		cc.setSaldo(saldo);
		
		CategoriaDeClientes limites = CategoriaDeClientes.defineCategoria(saldo) ;
		
		cc.setCategoria(limites);
		cc.setSenha(senha);
		cc.setTaxaMensal(limites.getTaxManutencao());
		cc.setLimiteConta(limites.getLimiteContaCC());
		cc.setLimeteCredito(limites.getLimiteCredito());
		
		long ccNum = geradorNumeroCC();
		cc.setNumeroConta(ccNum);
		
		
		
		
		return contaCorrenteRepository.save(cc);
	}

	
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
	
	

	//Saque em conta corrente
	public void sacar(BigDecimal saldo, BigDecimal valor)
	{	
		/*
		 * Método de saque em conta corrente considerando regras de negocio. 
		 * @limiteAtual: limite de conta corrente em cheque especial, é definido em cliente mas
		 * aqui é rechecado caso cliente tenho um valor maior em conta e tenha subido de categoria. 
		 * @limite é o valor do limite usado para calculo. 
		 * @diferencaValor: Será o novo valor da conta, que no calculo é acrescido 5% de juros. 
		 * 
		 */
		CategoriaDeClientes limiteAtual = CategoriaDeClientes.defineCategoria(saldo);
		BigDecimal limite = limiteAtual.getLimiteContaCC();
		
		if (valor.compareTo(saldo) <=  0) {
			saldo = saldo.subtract(valor);
		}
		else if (valor.compareTo(saldo.add(limite)) < 0)
		{
			BigDecimal diferencaValor = saldo.subtract(valor);
			BigDecimal deferencaValorMult = new BigDecimal(0.05);
			saldo = diferencaValor.add((diferencaValor.multiply(deferencaValorMult)));
		}
		else
		{
			System.out.println("Saldo insuficiente...");
		}
	}
	
	
	//Deposito conta corrente 
	
	public void depositar(BigDecimal valor, long numeroContaPattern)
	/**
	 * @param numeroContaPattern: Número da conta a recever
	 * @param valor: valor a depositar
	 * @param: saldo: saldo da conta
	 */
	{
		BigDecimal saldo = buscaConta(numeroContaPattern).getSaldo();
		saldo.add(valor);
	}
	//TRANFERENCIAS 
	public void transfereciaPix(BigDecimal valor,  long numeroConta, long contaReceb)
	{
		BigDecimal saldo = buscaConta(numeroConta).getSaldo();
		
		
		if( contaCheck(numeroConta) && saldo.compareTo(valor) >= 0)
		{
			
			depositar(valor, contaReceb) ;

		}else {
				System.out.println("Não foi possível fazer sua transferência."
						+ "Por favor confira o número da conta e o saldo.");
			}
	}
	

	

	
	
	//Numero conta corrente
	
	public long geradorNumeroCC()
	{
		/**
		 * Método ira gerar o numero da conta corrente.
		 * 
		 * @param randons: Intância de Random para geração de números aleatorios.
		 * @param contaCorrentePrimaryDigts: Três primeiros números fixos das CC que são 301.
		 * @param contaCcSecundary: Responsavél por gerar os outros 7 números.
		 * @param numeroContaProvisorio: Número da conta em final em String.
		 * @param numeroGerado: Número gerado para conta conrrente. 
		 * @param numeroConta: Número da conta convertido para long
		 * 
		 * @return: retorna o número conta
		 */
		Random randons = new Random();
		String numeroGerado = "";
		String numeroContaProvisorio = "";
		
		
		
		String contaCorrentePrimaryDigts = "301";
		
		for (int i = 0; i < 7; i++)
		{
			int contaCcSecundary =  randons.nextInt(9);
			numeroGerado += contaCcSecundary;
		}
		
		numeroContaProvisorio = contaCorrentePrimaryDigts + numeroGerado;
		
		
		long numeroConta = Long.parseLong(numeroContaProvisorio);
		
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

	
	
	// //listar contas
	// public void mostraContasCorrentes() {
	// 	 contaDao.listarContas();
		
	// }

	
	public List<Conta> getContasCorrente()
	{

		for (Conta c: contaCorrenteRepository.findAll())
		{
			if (c instanceof ContaCorrente)
			{
				System.out.println(c);
			}
		}
		return null;
	}

		public ContaCorrente buscaConta(long numeroConta)
	{
		/**
		 * Método que ira buscar uma conta poupanca considerando o número da conta.
		 * @param numeroConta: Número da conta a ser buscada.
		 * @return: Retorna a conta caso encontre.
		 * @return: Retorna null caso não encontre a conta.   
		 */
		ContaCorrente conta = contaCorrenteRepository.findByNumeroConta(numeroConta);
		
		return conta;
	}

	public boolean contaCheck(long numeroConta)
	{
		/**
		 * Método que ira verificar se a conta existe na base.
		 * @param numeroConta: Número da conta a ser verificada.
		 * @return: Retorna true caso encontre a conta.
		 * @return: Retorna false caso não encontre a conta.   
		 */
		ContaCorrente conta = contaCorrenteRepository.findByNumeroConta(numeroConta);
		if (conta == null)
		{
			return false;
		}
		
		return true;
	}

}
