package br.com.cdb.bancodigitallalinha.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity
public class Endereco {

    private String rua;
    private int numero;
    private String complemento = null;
    private String cidade;
    private String estado;
    private String cep;
    // @Id
    // @GeneratedValue(strategy = GenerationType.UUID)
    private UUID EnderecoId;


    public UUID getEnderecoId() {
        return EnderecoId;
    }

    public void setEnderecoId(UUID enderecoId) {
        EnderecoId = enderecoId;
    }

    public String getRua() {
        return rua;
    }


    public void setRua(String rua) {
        this.rua = rua;
    }


    public int getNumero() {
        return numero;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public String getComplemento() {
        return complemento;
    }


    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


    public String getCidade() {
        return cidade;
    }


    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getCep() {
        return cep;
    }


    public void setCep(String cep) {
        this.cep = cep;
    }
}
