package com.activity.structure.ListaEncadeada;

import com.activity.structure.Contato.Contato;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ListaEncadeadaListaTelefonica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lista_telefonica_id")
    private List<NoListaEncadeada> noListaEncadeadas = new ArrayList<>();

    public ListaEncadeadaListaTelefonica() {}

    public void adicionarContato(Contato contato) {
        NoListaEncadeada novoNoListaEncadeada = new NoListaEncadeada(contato);
        if (noListaEncadeadas.isEmpty() || contato.getNome().compareTo(noListaEncadeadas.get(0).getContato().getNome()) < 0) {
            noListaEncadeadas.add(0, novoNoListaEncadeada);
        } else {
            for (int i = 0; i < noListaEncadeadas.size() - 1; i++) {
                if (contato.getNome().compareTo(noListaEncadeadas.get(i + 1).getContato().getNome()) < 0) {
                    noListaEncadeadas.add(i + 1, novoNoListaEncadeada);
                    return;
                }
            }
            noListaEncadeadas.add(novoNoListaEncadeada);
        }
    }

    public void removerContato(String nome) {
        noListaEncadeadas.removeIf(noListaEncadeada -> noListaEncadeada.getContato().getNome().equals(nome));
        System.out.println(nome);
        System.out.println();
    }

    public Contato buscarContato(String nome) {
        for (NoListaEncadeada noListaEncadeada : noListaEncadeadas) {
            if (noListaEncadeada.getContato().getNome().equals(nome)) {
                return noListaEncadeada.getContato();
            }
        }
        return null;
    }


}
