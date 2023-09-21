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
        return this.repository.save(cliente);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return this.repository.findById(id);
    }

    public void excluirCliente(Long id) {
        this.repository.deleteById(id);
    }

}
