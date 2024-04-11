package com.activity.structure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class VerificarPalavrasService {

    @Autowired
    private  ArvoreBinariaBusca arvoreBinariaBusca;



        public boolean palavraEstaPresente(String palavra) {
            return arvoreBinariaBusca.contem(palavra);
        }
    }


