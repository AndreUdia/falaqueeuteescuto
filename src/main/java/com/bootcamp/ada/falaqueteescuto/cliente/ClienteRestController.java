package com.bootcamp.ada.falaqueteescuto.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return this.service.criarCliente(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = this.service.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = this.service.buscarClientePorId(id);

        if (clienteExistente.isPresent()) {
            Cliente clienteAtualizado = clienteExistente.get();

            clienteAtualizado.setNome(cliente.getNome());
            clienteAtualizado.setEmail(cliente.getEmail());
            clienteAtualizado.setDataNascimento(cliente.getDataNascimento());

            Cliente clienteSalvo = this.service.criarCliente(clienteAtualizado);
            return ResponseEntity.ok(clienteSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = this.service.buscarClientePorId(id);

        if (cliente.isPresent()) {
            this.service.excluirCliente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Cliente> clinteOptional = this.service.buscarClientePorId(id);

        if (clinteOptional.isPresent()) {
            Cliente clienteAtualizado = clinteOptional.get();

            atualizaDadosDoCliente(clienteAtualizado, updates);

            Cliente clienteSalvo = this.service.criarCliente(clienteAtualizado);

            return ResponseEntity.ok(clienteSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void atualizaDadosDoCliente(Cliente cliente, Map<String, Object> updates) {
        if (updates.containsKey("nome")) {
            cliente.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("email")) {
            cliente.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("dataNascimento")) {
            cliente.setDataNascimento((LocalDate) updates.get("dataNascimento"));
        }
    }
}
