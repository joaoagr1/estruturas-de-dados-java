package com.activity.structure;

public class ListaEncadeadaListaTelefonica {
    static class No {
        Contato contato;
        No proximo;

        public No(Contato contato) {
            this.contato = contato;
            this.proximo = null;
        }
    }

    No inicio;

    public ListaEncadeadaListaTelefonica() {
        inicio = null;
    }

    public void adicionarContato(Contato contato) {
        No novoNo = new No(contato);
        if (inicio == null || contato.nome.compareTo(inicio.contato.nome) < 0) {
            novoNo.proximo = inicio;
            inicio = novoNo;
        } else {
            No atual = inicio;
            while (atual.proximo != null && contato.nome.compareTo(atual.proximo.contato.nome) > 0) {
                atual = atual.proximo;
            }
            novoNo.proximo = atual.proximo;
            atual.proximo = novoNo;
        }
    }

    public void removerContato(String nome) {
        if (inicio == null) return;
        if (inicio.contato.nome.equals(nome)) {
            inicio = inicio.proximo;
            return;
        }
        No atual = inicio;
        while (atual.proximo != null && !atual.proximo.contato.nome.equals(nome)) {
            atual = atual.proximo;
        }
        if (atual.proximo != null) {
            atual.proximo = atual.proximo.proximo;
        }
    }

    public Contato buscarContato(String nome) {
        No atual = inicio;
        while (atual != null) {
            if (atual.contato.nome.equals(nome)) {
                return atual.contato;
            }
            atual = atual.proximo;
        }
        return null;
    }
}
