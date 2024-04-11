package com.activity.structure;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@Service
public class LerPalavrasChaveService {

    @Autowired
    private  ArvoreBinariaBusca arvoreBinariaBusca;
    private static final Logger logger = Logger.getLogger(LerPalavrasChaveService.class.getName());

    @Autowired
    public LerPalavrasChaveService(ArvoreBinariaBusca arvoreBinariaBusca) {
        this.arvoreBinariaBusca = arvoreBinariaBusca;

    }

    public void carregarPalavrasChaveDoArquivo() {
        String caminhoArquivo = "palavras_chave.txt";

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(caminhoArquivo)) {
            if (inputStream == null) {
                throw new RuntimeException("Arquivo de palavras-chave não encontrado em src/main/resources: " + caminhoArquivo);
            }
            try (BufferedReader leitor = new BufferedReader(new InputStreamReader(inputStream))) {
                String linha;
                while ((linha = leitor.readLine()) != null) {
                    arvoreBinariaBusca.inserir(linha.trim());
                }
            }
        } catch (IOException e) {
            logger.severe("Erro ao carregar palavras-chave do arquivo: " + e.getMessage());
            throw new RuntimeException("Falha ao carregar palavras-chave do arquivo", e);
        }

        System.out.println(arvoreBinariaBusca);
    }
}
