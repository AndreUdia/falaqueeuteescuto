package com.bootcamp.ada.falaqueteescuto.cliente;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteJpaRepository repository;

    public ClienteService(ClienteJpaRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos(){
        return this.repository.findAll();
    }
}
