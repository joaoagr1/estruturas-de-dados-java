package com.activity.structure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
    @Autowired
    private EntityManager entityManager;



    @Transactional
    @PostMapping("/criar-contato")
    public ResponseEntity<Contato> criarContato(@RequestBody Contato contato) {
        Contato newContato = new Contato(contato.nome, contato.telefone);
        entityManager.persist( newContato);
            return ResponseEntity.ok().body(newContato);
    }


    @Transactional
    @PostMapping("/criar-lista-telefonica")
    public ResponseEntity<VetorListaTelefonica> criarListaTelefonica(@RequestBody VetorListaTelefonica listaTelefonica) {
        VetorListaTelefonica newListaTelefonica = new VetorListaTelefonica(listaTelefonica);
        entityManager.persist(newListaTelefonica);
        return ResponseEntity.ok().body(newListaTelefonica);
    }


    @Transactional
    @GetMapping("/buscar-lista-telefonica/{id}")
    public ResponseEntity<VetorListaTelefonica> buscarListaTelefonica(@PathVariable Long id) {
        VetorListaTelefonica listaTelefonica = entityManager.find(VetorListaTelefonica.class, id);
        System.out.println(listaTelefonica);
        return ResponseEntity.ok().body(listaTelefonica);
    }

    @Transactional
    @PostMapping("/adicionar-contato/{contatoId}/{listaId}")
    public ResponseEntity<VetorListaTelefonica> adicionarContato(@PathVariable Long contatoId, @PathVariable Long listaId) {
        Contato contato = entityManager.find(Contato.class, contatoId);
        VetorListaTelefonica listaTelefonica = entityManager.find(VetorListaTelefonica.class, listaId);
        listaTelefonica.adicionarContato(contato);
        entityManager.persist(listaTelefonica);
        return ResponseEntity.ok().body(listaTelefonica);
    }

    @Transactional
    @DeleteMapping("/remover-contato/{contatoId}/{listaId}")
    public ResponseEntity<VetorListaTelefonica> removerContato(@PathVariable Long contatoId, @PathVariable Long listaId) {
        Contato contato = entityManager.find(Contato.class, contatoId);
        VetorListaTelefonica listaTelefonica = entityManager.find(VetorListaTelefonica.class, listaId);
        listaTelefonica.removerContato(contato.nome);
        entityManager.persist(listaTelefonica);
        return ResponseEntity.ok().body(listaTelefonica);
    }


}
