package com.activity.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "no")
@NoArgsConstructor
public class No {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noId;

    @OneToOne
    private Contato contato;

    public No(Contato contato) {
        this.contato = contato;
    }
}
