package com.bootcamp.ada.falaqueteescuto.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaJpaRepository extends JpaRepository<Empresa, Long> {
}
