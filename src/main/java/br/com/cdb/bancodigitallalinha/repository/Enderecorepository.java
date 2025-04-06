package br.com.cdb.bancodigitallalinha.repository;

import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigitallalinha.entity.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface Enderecorepository extends JpaRepository<Endereco, UUID>{

}
