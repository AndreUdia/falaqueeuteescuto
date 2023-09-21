package com.bootcamp.ada.falaqueteescuto.postagem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract sealed class Postagem permits Resposta, Reclamacao, Elogio {

    private UUID uuid;
    private String descricao;
    private LocalDateTime dataDaPostagem;
    private int classificacao;

    public abstract String getTipo();

}
