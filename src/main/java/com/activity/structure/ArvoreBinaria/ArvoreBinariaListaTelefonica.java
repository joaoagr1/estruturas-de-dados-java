package com.activity.structure.ArvoreBinaria;

import com.activity.structure.Contato.Contato;

public class ArvoreBinariaListaTelefonica {

NoArvoreBinaria raiz;

public ArvoreBinariaListaTelefonica() {
    raiz = null;
}

public void adicionarContato(Contato contato) {
    raiz = adicionarContatoRecursivo(raiz, contato);
}

private NoArvoreBinaria adicionarContatoRecursivo(NoArvoreBinaria no, Contato contato) {
    if (no == null) {
        return new NoArvoreBinaria(contato);
    }
    if (contato.getNome().compareTo(no.contato.getNome()) < 0) {
        no.esquerda = adicionarContatoRecursivo(no.esquerda, contato);
    } else if (contato.getNome().compareTo(no.contato.getNome()) > 0) {
        no.direita = adicionarContatoRecursivo(no.direita, contato);
    }
    return no;
}

public void removerContato(String nome) {
    raiz = removerContatoRecursivo(raiz, nome);
}

private NoArvoreBinaria removerContatoRecursivo(NoArvoreBinaria no, String nome) {
    if (no == null) return no;

    if (nome.compareTo(no.contato.getNome()) < 0) {
        no.esquerda = removerContatoRecursivo(no.esquerda, nome);
    } else if (nome.compareTo(no.contato.getNome()) > 0) {
        no.direita = removerContatoRecursivo(no.direita, nome);
    } else {
        if (no.esquerda == null) return no.direita;
        else if (no.direita == null) return no.esquerda;

        no.contato = encontrarMenorValor(no.direita);
        no.direita = removerContatoRecursivo(no.direita, no.contato.getNome());
    }
    return no;
}

private Contato encontrarMenorValor(NoArvoreBinaria no) {
    while (no.esquerda != null) {
        no = no.esquerda;
    }
    return no.contato;
}

public Contato buscarContato(String nome) {
    return buscarContatoRecursivo(raiz, nome);
}

private Contato buscarContatoRecursivo(NoArvoreBinaria no, String nome) {
    if (no == null || no.contato.getNome().equals(nome)) {
        return no != null ? no.contato : null;
    }

    if (nome.compareTo(no.contato.getNome()) < 0) {
        return buscarContatoRecursivo(no.esquerda, nome);
    }
    return buscarContatoRecursivo(no.direita, nome);
}
}
