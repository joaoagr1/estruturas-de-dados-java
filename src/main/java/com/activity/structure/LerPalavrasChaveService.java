package com.activity.structure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Data
public class LerPalavrasChaveService {
    private final ArvoreBinariaBusca arvoreBinariaBusca;

    @Autowired
    public LerPalavrasChaveService(ArvoreBinariaBusca arvoreBinariaBusca) {
        this.arvoreBinariaBusca = arvoreBinariaBusca;
    }

    public void carregarPalavrasChaveDoArquivo() {
        String caminhoArquivo = "palavras_chave.txt";

        // Usando getResourceAsStream para ler o arquivo de src/main/resources
        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(caminhoArquivo)))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                arvoreBinariaBusca.inserir(linha.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Trate exceções conforme necessário
        }
    }
}
