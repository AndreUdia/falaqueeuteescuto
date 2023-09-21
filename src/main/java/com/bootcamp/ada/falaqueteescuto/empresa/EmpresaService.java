package com.bootcamp.ada.falaqueteescuto.empresa;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaJpaRepository repository;

    public EmpresaService(EmpresaJpaRepository repository) {
        this.repository = repository;
    }

    public List<Empresa> listarTodas(){
        return this.repository.findAll();
    }

    public Empresa criarEmpresa(Empresa empresa) {
        return this.repository.save(empresa);
    }

    public Optional<Empresa> buscarEmpresaPorId(Long id) {
        return this.repository.findById(id);
    }

    public void excluirEmpresa(Long id) {
        this.repository.deleteById(id);
    }

}