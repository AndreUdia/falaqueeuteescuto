package com.bootcamp.ada.falaqueteescuto.postagem;

public non-sealed class Reclamacao extends Postagem{
    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
}
