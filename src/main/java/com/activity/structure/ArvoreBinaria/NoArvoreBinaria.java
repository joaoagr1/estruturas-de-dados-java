package com.activity.structure.ArvoreBinaria;

import com.activity.structure.Contato.Contato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "noArvoreBinaria")
@NoArgsConstructor
public class NoArvoreBinaria {

    @JsonIgnore
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;

    @ManyToOne
    @JoinColumn(name = "esquerda_id")
    private NoArvoreBinaria esquerda;

    @ManyToOne
    @JoinColumn(name = "direita_id")
    private NoArvoreBinaria direita;


    public NoArvoreBinaria(Contato contato) {
        this.contato = contato;
        esquerda = direita = null;
    }

}

