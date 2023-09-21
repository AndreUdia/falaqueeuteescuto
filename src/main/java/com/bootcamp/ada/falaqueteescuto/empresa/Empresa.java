package com.bootcamp.ada.falaqueteescuto.empresa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_seq")
    @SequenceGenerator(name = "empresa_seq", sequenceName = "EMPRESA_SEQ", allocationSize = 1)
    private Long id;

    private UUID uuid;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Setor setor;

    enum Setor {
        ACOUGUE, BANCO, FLORICULTURA, LIVRARIA, OFICINA, PADARIA
    }
}
