package com.activity.structure.ListaEncadeada;

import com.activity.structure.Contato.Contato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "no")
@NoArgsConstructor
public class NoListaEncadeada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noId;

    @OneToOne
    private Contato contato;

    public NoListaEncadeada(Contato contato) {
        this.contato = contato;
    }
}
