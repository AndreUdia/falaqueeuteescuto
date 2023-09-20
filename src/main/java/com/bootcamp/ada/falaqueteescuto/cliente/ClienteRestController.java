package com.bootcamp.ada.falaqueteescuto.cliente;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    private final ClienteService service;

    public ClienteRestController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listarTodos(){
        return this.service.listarTodos();
    }
}
