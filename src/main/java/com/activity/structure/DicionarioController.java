package com.activity.structure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Data
public class DicionarioController {

    @Autowired
    private  VerificarPalavrasService verificarPalavrasService;



    @GetMapping("/palavra-chave")
    public boolean palavraEstaPresente(@RequestParam String palavra) {
        return verificarPalavrasService.palavraEstaPresente(palavra);
    }
}
