package com.activity.structure;

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
    private List<No> nos = new ArrayList<>();

    public ListaEncadeadaListaTelefonica() {}

    public void adicionarContato(Contato contato) {
        No novoNo = new No(contato);
        if (nos.isEmpty() || contato.getNome().compareTo(nos.get(0).getContato().getNome()) < 0) {
            nos.add(0, novoNo);
        } else {
            for (int i = 0; i < nos.size() - 1; i++) {
                if (contato.getNome().compareTo(nos.get(i + 1).getContato().getNome()) < 0) {
                    nos.add(i + 1, novoNo);
                    return;
                }
            }
            nos.add(novoNo);
        }
    }

    public void removerContato(String nome) {
        nos.removeIf(no -> no.getContato().getNome().equals(nome));
    }

    public Contato buscarContato(String nome) {
        for (No no : nos) {
            if (no.getContato().getNome().equals(nome)) {
                return no.getContato();
            }
        }
        return null;
    }


}
