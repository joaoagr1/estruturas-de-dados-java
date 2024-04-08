package com.activity.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Contato")
@NoArgsConstructor
@AllArgsConstructor
public class Contato {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Integer contatoId;

private String nome;
private String telefone;

public Contato(String nome, String telefone) {
    this.nome = nome;
    this.telefone = telefone;
}
}