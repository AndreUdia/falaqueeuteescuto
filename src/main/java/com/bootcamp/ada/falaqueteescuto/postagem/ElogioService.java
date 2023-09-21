package com.bootcamp.ada.falaqueteescuto.postagem;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElogioService {

    private final ElogioJpaRepository repository;

    public ElogioService(ElogioJpaRepository repository) {
        this.repository = repository;
    }

    public List<Elogio> listarTodos(){
        return this.repository.findAll();
    }

    public Elogio criarElogio(Elogio elogio) {
        return this.repository.save(elogio);
    }

    public Optional<Elogio> buscarElogioPorId(Long id) {
        return this.repository.findById(id);
    }

    public void excluirElogio(Long id) {
        this.repository.deleteById(id);
    }

}
