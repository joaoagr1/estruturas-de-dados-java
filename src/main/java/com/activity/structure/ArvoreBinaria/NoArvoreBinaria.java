package com.activity.structure.ArvoreBinaria;

import com.activity.structure.Contato.Contato;

public class NoArvoreBinaria {

    Contato contato;
    NoArvoreBinaria esquerda, direita;

    public NoArvoreBinaria(Contato contato) {
        this.contato = contato;
        esquerda = direita = null;
    }

}

