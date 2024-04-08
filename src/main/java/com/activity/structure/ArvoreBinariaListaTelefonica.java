package com.activity.structure;

public class ArvoreBinariaListaTelefonica {
static class No {
    Contato contato;
    No esquerda, direita;

    public No(Contato contato) {
        this.contato = contato;
        esquerda = direita = null;
    }
}

No raiz;

public ArvoreBinariaListaTelefonica() {
    raiz = null;
}

public void adicionarContato(Contato contato) {
    raiz = adicionarContatoRecursivo(raiz, contato);
}

private No adicionarContatoRecursivo(No no, Contato contato) {
    if (no == null) {
        return new No(contato);
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

private No removerContatoRecursivo(No no, String nome) {
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

private Contato encontrarMenorValor(No no) {
    while (no.esquerda != null) {
        no = no.esquerda;
    }
    return no.contato;
}

public Contato buscarContato(String nome) {
    return buscarContatoRecursivo(raiz, nome);
}

private Contato buscarContatoRecursivo(No no, String nome) {
    if (no == null || no.contato.getNome().equals(nome)) {
        return no != null ? no.contato : null;
    }

    if (nome.compareTo(no.contato.getNome()) < 0) {
        return buscarContatoRecursivo(no.esquerda, nome);
    }
    return buscarContatoRecursivo(no.direita, nome);
}
}
