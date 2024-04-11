package com.activity.structure.ArvoreBinaria;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RestController;
import com.activity.structure.Contato.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/arvore-binaria")
public class ArvoreBinariaController {

    @Autowired
    private ArvoreBinariaListaTelefonica arvoreBinariaListaTelefonica;

    @Autowired
    private ArvoreBinariaRepository arvoreBinariaRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @PostMapping("/adicionar")
    public ResponseEntity<ArvoreBinariaListaTelefonica> adicionarContato(@RequestBody Contato contato) {
        arvoreBinariaListaTelefonica.adicionarContato(contato);
        entityManager.persist(contato);
        return ResponseEntity.ok(arvoreBinariaListaTelefonica);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Contato> buscarContato(@RequestBody Map<String, String> request) {
        Contato contato = arvoreBinariaListaTelefonica.buscarContato(request.get("nome"));
        if (contato != null) {
            return ResponseEntity.ok(contato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remover")
    public ResponseEntity<Void> removerContato(@RequestParam String nome) {
        arvoreBinariaListaTelefonica.removerContato(nome);
        entityManager.persist(arvoreBinariaListaTelefonica);
        return ResponseEntity.ok().build();
    }
}
