package com.luispedro.tarefas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luispedro.tarefas.model.entity.Tarefa;
import com.luispedro.tarefas.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	private final TarefaService service;

	public TarefaController(TarefaService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
		Tarefa salva = service.criarTarefa(tarefa);
		return ResponseEntity.created(URI.create("/tarefas/" + salva.getId())).body(salva);
	}

	@GetMapping
	public ResponseEntity<List<Tarefa>> listarTodas() {
		return ResponseEntity.ok(service.listarTodas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> alterar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
		tarefa.setId(id);
		return ResponseEntity.ok(service.alterarTarefa(tarefa));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		service.excluirTarefa(id);
		return ResponseEntity.noContent().build();
	}

}
