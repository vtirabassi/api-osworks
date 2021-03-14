package br.com.tirabassi.osworksapi.controller;

import br.com.tirabassi.osworksapi.domain.model.Cliente;
import br.com.tirabassi.osworksapi.domain.repository.ClienteRepository;
import br.com.tirabassi.osworksapi.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    public ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes(){

        return ResponseEntity.ok(repository.findAll());

    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> getCliente(@PathVariable(name = "id_cliente") Long idCliente){
        Optional<Cliente> optionalCliente = repository.findById(idCliente);

        if(optionalCliente.isPresent()){
            return ResponseEntity.ok(optionalCliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> addCliente(@Valid @RequestBody Cliente cliente){

        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @PutMapping("/{id_cliente}")
    public ResponseEntity<Cliente> atualizarCliente(@Valid
                                                    @PathVariable(name = "id_cliente") Long idCliente,
                                                    @RequestBody Cliente cliente){

        if(!repository.existsById(idCliente)){
            return ResponseEntity.notFound().build();
        }

        cliente.setId(idCliente);
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @DeleteMapping("/{id_cliente}")
    public ResponseEntity deletarCliente(@PathVariable(name = "id_cliente") Long idCliente){

        if(!repository.existsById(idCliente)){
            return ResponseEntity.notFound().build();
        }

        clienteService.deleteById(idCliente);
        return ResponseEntity.noContent().build();
    }
}
