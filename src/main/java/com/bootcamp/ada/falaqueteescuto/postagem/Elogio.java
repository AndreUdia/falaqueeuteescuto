package com.bootcamp.ada.falaqueteescuto.postagem;

import jakarta.persistence.*;

@Entity
public non-sealed class Elogio extends Postagem{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postagem_seq")
    @SequenceGenerator(name = "postagem_seq", sequenceName = "POSTAGEM_SEQ", allocationSize = 1)
    private Long id;
    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
}
