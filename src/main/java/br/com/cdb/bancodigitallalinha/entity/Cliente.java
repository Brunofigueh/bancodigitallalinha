package br.com.cdb.bancodigitallalinha.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Cliente {




    private String nome;
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    
    @Id
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


    public void setDataNascimento(String dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }
    public String getDataNascimento()
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
