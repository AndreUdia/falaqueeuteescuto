package com.bootcamp.ada.falaqueteescuto.postagem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/elogios")
public class ElogioRestController {
    private final ElogioService service;
    Logger logger = LoggerFactory.getLogger(ElogioRestController.class);


    public ElogioRestController(ElogioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Elogio> listarTodas(){
        return this.service.listarTodos();
    }

    @PostMapping
    public Elogio criarElogio(@RequestBody Elogio elogio) {
        return this.service.criarElogio(elogio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Elogio> buscarElogioPorId(@PathVariable Long id) {
        Optional<Elogio> elogio = this.service.buscarElogioPorId(id);
        return elogio.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Elogio> atualizarElogio(@PathVariable Long id, @RequestBody Elogio Elogio) {
        Optional<Elogio> ElogioExistente = this.service.buscarElogioPorId(id);

        if (ElogioExistente.isPresent()) {
            Elogio elogioAtualizada = ElogioExistente.get();

            elogioAtualizada.setDescricao(Elogio.getDescricao());
            elogioAtualizada.setClassificacao(Elogio.getClassificacao());

            Elogio elogioSalva = this.service.criarElogio(elogioAtualizada);
            return ResponseEntity.ok(elogioSalva);
        } else {
            logger.debug(LocalDateTime.now() + ": Tentativa de edição da Elogio de Id: " + id + " - não encontrada");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirElogio(@PathVariable Long id) {
        Optional<Elogio> elogio = this.service.buscarElogioPorId(id);

        if (elogio.isPresent()) {
            this.service.excluirElogio(id);
            return ResponseEntity.noContent().build();
        } else {
            logger.debug(LocalDateTime.now() + ": Tentativa de exclusão do Elogio de Id: " + id + " - não encontrada");
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Elogio> updateElogio(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Elogio> elogioOptional = this.service.buscarElogioPorId(id);

        if (elogioOptional.isPresent()) {
            Elogio elogioAtualizada = elogioOptional.get();

            atualizaDadosDoElogio(elogioAtualizada, updates);

            Elogio elogioSalva = this.service.criarElogio(elogioAtualizada);

            return ResponseEntity.ok(elogioSalva);
        } else {
            logger.debug(LocalDateTime.now() + ": Tentativa de atualização do Elogio de Id: " + id + " - não encontrada");
            return ResponseEntity.notFound().build();
        }
    }

    private void atualizaDadosDoElogio(Elogio elogio, Map<String, Object> updates) {
        if (updates.containsKey("nome")) {
            elogio.setDescricao((String) updates.get("descricao"));
        }
        if (updates.containsKey("classificacao")) {
            elogio.setClassificacao((int) updates.get("classificacao"));
        }
    }
}
