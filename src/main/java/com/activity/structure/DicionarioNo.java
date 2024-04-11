package com.activity.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "DicionarioNo")
@NoArgsConstructor
public class DicionarioNo {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    String chave;
    @Transient
    DicionarioNo esquerda, direita;

    DicionarioNo(String chave) {
        this.chave = chave;
        esquerda = direita = null;
    }

}
