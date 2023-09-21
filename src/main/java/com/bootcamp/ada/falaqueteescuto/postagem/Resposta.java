package com.bootcamp.ada.falaqueteescuto.postagem;

public non-sealed class Resposta extends Postagem{
    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
}
