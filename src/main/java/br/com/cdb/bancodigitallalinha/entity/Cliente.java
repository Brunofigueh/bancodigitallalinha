package br.com.cdb.bancodigitallalinha.entity;

import jakarta.persistence.Entity;
import java.util.UUID;

@Entity
public class Cliente {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private Endereco endereco;
    private UUID clienteID;

    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public String getNome()
    {
        return nome;
    }


    public void setCPF(String cpf)
    {
        this.cpf = cpf;
    }
    public String getCPF()
    {
        return cpf;
    }


    public void setDataNasc(String dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }
    public String getDataNasc()
    {
        return dataNascimento;
    }




    public Endereco getEndereco() 
    {
        return endereco;
    }
    public void setEndereco(Endereco endereco) 
    {
        this.endereco = endereco;
    }

    public UUID getClienteID()
    {
        return clienteID;
    }
    public void setClienteID(UUID clienteID) 
    {
        this.clienteID = clienteID;
    }
}
