package br.com.tirabassi.osworksapi.domain.service;

import br.com.tirabassi.osworksapi.domain.model.Cliente;
import br.com.tirabassi.osworksapi.domain.repository.ClienteRepository;
import br.com.tirabassi.osworksapi.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente save(Cliente cliente) {

        Cliente clienteExistente = repository.findByEmail(cliente.getEmail());

        if(clienteExistente != null && !clienteExistente.equals(cliente)){
            throw new NegocioException("Ja existe um cliente com esse e-mail");
        }

        return repository.save(cliente);
    }

    public void deleteById(Long idCliente) {

        repository.deleteById(idCliente);
    }
}
