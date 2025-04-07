package br.com.cdb.bancodigitallalinha.repository;


import br.com.cdb.bancodigitallalinha.entity.Cliente;
import br.com.cdb.bancodigitallalinha.entity.Endereco;

import java.util.ArrayList;


public class Enderecorepository {

    ArrayList<Endereco> enderecoDB =new ArrayList<>();
	
	Cliente cliente = new Cliente();
	
	public void addEndereco(Endereco endereco)
	{
		enderecoDB.add(endereco);
	}

}
