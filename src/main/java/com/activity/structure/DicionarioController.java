package com.activity.structure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Data
public class DicionarioController {

    @Autowired
    private  VerificarPalavrasService verificarPalavrasService;

    @Autowired
    private LerPalavrasChaveService lerPalavrasChaveService;

    @GetMapping("/palavra-chave")
    public boolean palavraEstaPresente(@RequestParam String palavra) {
        return verificarPalavrasService.palavraEstaPresente(palavra);
    }

    @GetMapping("/carregar")
    public ResponseEntity<String> carregar() {
        lerPalavrasChaveService.carregarPalavrasChaveDoArquivo();
        return ResponseEntity.ok("OK");
    }
}
