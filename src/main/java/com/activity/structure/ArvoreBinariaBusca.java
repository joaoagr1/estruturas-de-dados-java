package com.activity.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity(name = "ArvoreBinariaBusca")
@Data
@Component
public class ArvoreBinariaBusca {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    @Transient
    private DicionarioNo raiz;

    public ArvoreBinariaBusca() {
        raiz = null;
    }

    // Método para inserir uma palavra na árvore
    public void inserir(String chave) {
        chave = chave.toLowerCase(); // Normaliza a palavra para minúsculas
        raiz = inserirRecursivamente(raiz, chave);
    }

    private DicionarioNo inserirRecursivamente(DicionarioNo raiz, String chave) {
        if (raiz == null) {
            raiz = new DicionarioNo(chave);
            return raiz;
        }

        // Comparação sem distinção de maiúsculas e minúsculas
        int comparacao = chave.compareTo(raiz.chave);
        if (comparacao < 0) {
            raiz.esquerda = inserirRecursivamente(raiz.esquerda, chave);
        } else if (comparacao > 0) {
            raiz.direita = inserirRecursivamente(raiz.direita, chave);
        }

        return raiz;
    }

    // Método para verificar se uma palavra está presente na árvore
    public boolean contem(String chave) {
        chave = chave.toLowerCase(); // Normaliza a palavra para minúsculas
        return contemRecursivamente(raiz, chave);
    }

    private boolean contemRecursivamente(DicionarioNo raiz, String chave) {
        if (raiz == null) {
            return false;
        }

        // Comparação sem distinção de maiúsculas e minúsculas
        int comparacao = chave.compareTo(raiz.chave);
        if (comparacao == 0) {
            return true;
        } else if (comparacao < 0) {
            return contemRecursivamente(raiz.esquerda, chave);
        } else {
            return contemRecursivamente(raiz.direita, chave);
        }
    }
}
