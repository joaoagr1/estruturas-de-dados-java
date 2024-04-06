package com.activity.structure;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class VetorListaTelefonica {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Adicionando um identificador

    @OneToMany(cascade = CascadeType.ALL)
    private List<Contato> contatos;

    public VetorListaTelefonica(VetorListaTelefonica listaTelefonica) {
        contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato) {
        contatos.add(contato);
    }

    public void removerContato(String nome) {
        contatos.removeIf(c -> c.nome.equals(nome));
    }

    public Contato buscarContato(String nome) {
        for (Contato contato : contatos) {
            if (contato.nome.equals(nome)) {
                return contato;
            }
        }
        return null;
    }
}