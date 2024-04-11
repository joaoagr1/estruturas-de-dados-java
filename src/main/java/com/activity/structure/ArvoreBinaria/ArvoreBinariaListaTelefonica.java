package com.activity.structure.ArvoreBinaria;

import com.activity.structure.Contato.Contato;
import com.activity.structure.ListaEncadeada.NoListaEncadeada;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ArvoreBinariaListaTelefonica")
@Data
@Component
public class ArvoreBinariaListaTelefonica {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    NoArvoreBinaria raiz;


//    @Transient
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "arvore_binaria_lista_telefonica_id")
//    private List<NoArvoreBinaria> noArvoreBinaria = new ArrayList<>();

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
        if (contato.getNome().compareTo(no.getContato().getNome()) < 0) {
            no.setEsquerda(adicionarContatoRecursivo(no.getEsquerda(), contato));
        } else if (contato.getNome().compareTo(no.getContato().getNome()) > 0) {
            no.setDireita(adicionarContatoRecursivo(no.getDireita(), contato));
        }
        return no;
    }

    public void removerContato(String nome) {
        raiz = removerContatoRecursivo(raiz, nome);
    }

    private NoArvoreBinaria removerContatoRecursivo(NoArvoreBinaria no, String nome) {
        if (no == null) return no;

        if (nome.compareTo(no.getContato().getNome()) < 0) {
            no.setEsquerda(removerContatoRecursivo(no.getEsquerda(), nome));
        } else if (nome.compareTo(no.getContato().getNome()) > 0) {
            no.setDireita(removerContatoRecursivo(no.getDireita(), nome));
        } else {
            if (no.getEsquerda() == null) return no.getDireita();
            else if (no.getDireita() == null) return no.getEsquerda();

            no.setContato(encontrarMenorValor(no.getDireita()));
            no.setDireita(removerContatoRecursivo(no.getDireita(), no.getContato().getNome()));
        }
        return no;
    }

    private Contato encontrarMenorValor(NoArvoreBinaria no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no.getContato();
    }

    public Contato buscarContato(String nome) {
        return buscarContatoRecursivo(raiz, nome);
    }

    private Contato buscarContatoRecursivo(NoArvoreBinaria no, String nome) {
        if (no == null || no.getContato().getNome().equals(nome)) {
            return no != null ? no.getContato() : null;
        }

        if (nome.compareTo(no.getContato().getNome()) < 0) {
            return buscarContatoRecursivo(no.getEsquerda(), nome);
        }
        return buscarContatoRecursivo(no.getDireita(), nome);
    }
}
