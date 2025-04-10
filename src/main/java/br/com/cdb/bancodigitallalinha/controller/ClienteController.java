package br.com.cdb.bancodigitallalinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigitallalinha.entity.Cliente;
import br.com.cdb.bancodigitallalinha.repository.ClienteRepository;
import br.com.cdb.bancodigitallalinha.service.ClienteService;




@RestController
@RequestMapping("/cliente")
public class ClienteController {


    // @Autowired
    // private Cliente cliente;

    @Autowired
    private ClienteRepository clienteRepository ;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/add")
    public ResponseEntity<String> addCliente(@RequestBody Cliente cliente) {
       
        Cliente clienteAdded = clienteService.addCliente(cliente.getNome(), cliente.getCPF(), cliente.getDataNascimento(), cliente.getEndereco(), cliente.getClienteID());

        if (clienteAdded != null) 
        {
            return new ResponseEntity<>("Clente adicionado "+ cliente.getNome()+ ". Adicionado com sucesso", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Erro ao adicionar cliente", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Cliente>> getAllClinete()
    {
         List<Cliente> clientes = clienteService.getClientes();
         return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }


    @GetMapping("/mostrar")
    public  void  mostrarCliente(@RequestBody String cpf )
     {
        Cliente cliente = new Cliente();
    
        if (cliente.getCPF() != cpf)
        {
             new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        new ResponseEntity<>(clienteRepository.getReferenceById(cliente.getClienteID()), HttpStatus.OK);
     }

     @GetMapping("/setCliennte")
     public ResponseEntity<Cliente> setarCliente()
     {
        Cliente cliente = new Cliente();

        if (clienteRepository.getReferenceById(cliente.getClienteID()) == null)
        {
            return new ResponseEntity<Cliente>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clienteRepository.getReferenceById(cliente.getClienteID()), HttpStatus.OK);
     }

}

