package com.activity.structure;

import com.activity.structure.ListaEncadeadaListaTelefonica;
import com.activity.structure.Contato;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividade-lista-encadeada")
public class ListaEncadeadaController {

    @Autowired
    private ListaEncadeadaListaTelefonicaRepository listaTelefonicaRepository;
    @Autowired
    private EntityManager entityManager;

    @PostMapping("/criar")
    public ResponseEntity<ListaEncadeadaListaTelefonica> criarListaTelefonica() {
        ListaEncadeadaListaTelefonica novaLista = new ListaEncadeadaListaTelefonica();
        ListaEncadeadaListaTelefonica listaSalva = listaTelefonicaRepository.save(novaLista);
        return ResponseEntity.ok().body(listaSalva);
    }

    @Transactional
    @PostMapping("/{id}/adicionar-contato")
    public ResponseEntity<ListaEncadeadaListaTelefonica> adicionarContato(@PathVariable Long id, @RequestBody Contato contato) {
        ListaEncadeadaListaTelefonica lista = listaTelefonicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lista telefônica não encontrada"));
        lista.adicionarContato(contato);
        entityManager.persist(contato);
        entityManager.persist(lista);
        return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{id}/remover-contato/{nome}")
    public ResponseEntity<?> removerContato(@PathVariable Long id, @PathVariable String nome) {
        ListaEncadeadaListaTelefonica lista = listaTelefonicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lista telefônica não encontrada"));
        lista.removerContato(nome);
        listaTelefonicaRepository.save(lista);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/buscar-contato/{nome}")
    public ResponseEntity<Contato> buscarContato(@PathVariable Long id, @PathVariable String nome) {
        ListaEncadeadaListaTelefonica lista = listaTelefonicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lista telefônica não encontrada"));
        Contato contato = lista.buscarContato(nome);
        if (contato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(contato);
    }
}
