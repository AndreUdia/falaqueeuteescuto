package com.bootcamp.ada.falaqueteescuto.postagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElogioJpaRepository extends JpaRepository<Elogio, Long> {
}
