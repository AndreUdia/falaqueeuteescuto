package com.bootcamp.ada.falaqueteescuto.cliente;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteJpaRepository repository;

    public ClienteService(ClienteJpaRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos(){
        return this.repository.findAll();
    }

    public Cliente criarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return repository.findById(id);
    }

    public void excluirCliente(Long id) {
        repository.deleteById(id);
    }

}
