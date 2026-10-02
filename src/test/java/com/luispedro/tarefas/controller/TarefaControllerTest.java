package com.luispedro.tarefas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.luispedro.tarefas.model.entity.Tarefa;
import com.luispedro.tarefas.model.enums.StatusTarefa;
import com.luispedro.tarefas.service.TarefaService;

public class TarefaControllerTest {

	private final TarefaService service = mock(TarefaService.class);
	private final TarefaController controller = new TarefaController(service);

	private Tarefa novaTarefa() {
		return Tarefa.builder()
				.id(1L)
				.nome("Tarefa 1")
				.descricao("Descrição")
				.observacao("Observação")
				.status(StatusTarefa.PENDENTE)
				.dataCriacao(LocalDateTime.now())
				.dataAtualizacao(LocalDateTime.now())
				.build();
	}

	@Test
	public void deveCriarTarefa() {
		when(service.criarTarefa(any(Tarefa.class))).thenReturn(novaTarefa());

		ResponseEntity<Tarefa> resposta = controller.criar(novaTarefa());

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertNotNull(resposta.getBody());
		assertEquals("Tarefa 1", resposta.getBody().getNome());
	}

	@Test
	public void deveListarTarefas() {
		when(service.listarTodas()).thenReturn(List.of(novaTarefa()));

		ResponseEntity<List<Tarefa>> resposta = controller.listarTodas();

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(1, resposta.getBody().size());
	}

	@Test
	public void deveBuscarTarefaPorId() {
		when(service.buscarPorId(1L)).thenReturn(Optional.of(novaTarefa()));

		ResponseEntity<Tarefa> resposta = controller.buscarPorId(1L);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(1L, resposta.getBody().getId());
	}

	@Test
	public void deveAlterarTarefa() {
		when(service.alterarTarefa(any(Tarefa.class))).thenReturn(novaTarefa());

		ResponseEntity<Tarefa> resposta = controller.alterar(1L, novaTarefa());

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	public void deveExcluirTarefa() {
		ResponseEntity<Void> resposta = controller.excluir(1L);

		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	}

}
