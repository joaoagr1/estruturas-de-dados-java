package com.activity.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Contato")
public class Contato {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Integer contatoId;

String nome;
String telefone;

public Contato(String nome, String telefone) {
    this.nome = nome;
    this.telefone = telefone;
}
}