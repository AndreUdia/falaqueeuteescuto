package com.bootcamp.ada.falaqueteescuto.empresa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaRestController {

    private final EmpresaService service;

    Logger logger = LoggerFactory.getLogger(EmpresaRestController.class);

    public EmpresaRestController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Empresa> listarTodas(){
        return this.service.listarTodas();
    }

    @PostMapping
    public Empresa criarEmpresa(@RequestBody Empresa empresa) {
        return this.service.criarEmpresa(empresa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = this.service.buscarEmpresaPorId(id);
        return empresa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        Optional<Empresa> empresaExistente = this.service.buscarEmpresaPorId(id);

        if (empresaExistente.isPresent()) {
            Empresa empresaAtualizada = empresaExistente.get();

            empresaAtualizada.setNome(empresa.getNome());
            empresaAtualizada.setSetor(empresa.getSetor());

            Empresa empresaSalva = this.service.criarEmpresa(empresaAtualizada);
            return ResponseEntity.ok(empresaSalva);
        } else {
            logger.debug(LocalDateTime.now() + ": Tentativa de edição de empresa de Id: " + id + " - não encontrada");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = this.service.buscarEmpresaPorId(id);

        if (empresa.isPresent()) {
            this.service.excluirEmpresa(id);
            return ResponseEntity.noContent().build();
        } else {
            logger.debug(LocalDateTime.now() + ": Tentativa de exclusão de empresa de Id: " + id + " - não encontrada");
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Empresa> empresaOptional = this.service.buscarEmpresaPorId(id);

        if (empresaOptional.isPresent()) {
            Empresa empresaAtualizada = empresaOptional.get();

            atualizaDadosDaEmpresa(empresaAtualizada, updates);

            Empresa empresaSalva = this.service.criarEmpresa(empresaAtualizada);

            return ResponseEntity.ok(empresaSalva);
        } else {
            logger.debug(LocalDateTime.now() + ": Tentativa de atualização de empresa de Id: " + id + " - não encontrada");
            return ResponseEntity.notFound().build();
        }
    }

    private void atualizaDadosDaEmpresa(Empresa empresa, Map<String, Object> updates) {
        if (updates.containsKey("nome")) {
            empresa.setNome((String) updates.get("nome"));
        }
        if (updates.containsKey("setor")) {
            empresa.setSetor(Empresa.Setor.valueOf((String) updates.get("setor")));
        }
    }
}
