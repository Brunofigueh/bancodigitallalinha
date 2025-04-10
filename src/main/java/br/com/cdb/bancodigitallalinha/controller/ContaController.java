package br.com.cdb.bancodigitallalinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigitallalinha.entity.Conta;
import br.com.cdb.bancodigitallalinha.entity.ContaCorrente;
import br.com.cdb.bancodigitallalinha.entity.ContaPoupanca;
import br.com.cdb.bancodigitallalinha.repository.ContaRepository;


@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;
    
    @PostMapping("/addContaCorrente")
    public ResponseEntity<String> addContaCorrente(@RequestBody ContaCorrente contaCorrente) {
        
        if (contaCorrente != null )
        {
            return new ResponseEntity<>("Conta Corrente criada com sucesso "+ contaCorrente.getNumeroConta(), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Erro ao adcionar conta corrente",HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/addContaPoupanca")
    public ResponseEntity<String> addContaPoupanca(@RequestBody ContaPoupanca contaPoupanca) 
    {
        if (contaPoupanca == null)
        {
            return new ResponseEntity<>("Erro ao adicionar conta poupanca",HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Conta Poupança adicionada com sucesso"+ contaPoupanca.getNumeroConta(),HttpStatus.CREATED);

    }

    @PostMapping("/listCCorrente")
    public ResponseEntity<List<Conta>> gerContaCorrente()
    {
        List<Conta> contas = contaRepository.findAll();
        return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);    
    }

    @PostMapping("/listCPoupanca")
    public ResponseEntity<List<Conta>> gerContaPoupanca()
    {
        List<Conta> contas = contaRepository.findAll();
        return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);    
    }

    

}
