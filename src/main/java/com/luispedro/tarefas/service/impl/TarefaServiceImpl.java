package com.luispedro.tarefas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luispedro.tarefas.exeption.RegraNegocioException;
import com.luispedro.tarefas.model.entity.Tarefa;
import com.luispedro.tarefas.model.enums.StatusTarefa;
import com.luispedro.tarefas.model.repository.TarefaRepository;
import com.luispedro.tarefas.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService{

	private TarefaRepository repository;

	@Autowired
	public TarefaServiceImpl(TarefaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Tarefa criarTarefa(Tarefa tarefa) {
		validarTarefa(tarefa);
		if (tarefa.getStatus() == null) {
			tarefa.setStatus(StatusTarefa.PENDENTE);
		}
		return repository.save(tarefa);
	}

	@Override
	public Tarefa alterarTarefa(Tarefa tarefa) {
		if (tarefa == null || tarefa.getId() == null) {
			throw new RegraNegocioException("Para alterar uma tarefa é necessário informar o seu id.");
		}
		validarTarefa(tarefa);
		if (!repository.existsById(tarefa.getId())) {
			throw new RegraNegocioException("Tarefa não encontrada para o id: " + tarefa.getId());
		}
		return repository.save(tarefa);
	}

	@Override
    public void excluirTarefa(Long id) {
		if (id == null || !repository.existsById(id)) {
			throw new RegraNegocioException("Tarefa não encontrada para o id: " + id);
		}
        repository.deleteById(id);
    }

	@Override
    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    private void validarTarefa(Tarefa tarefa) {
        if (tarefa == null) {
            throw new RegraNegocioException("A tarefa não pode ser nula.");
        }
        if (tarefa.getNome() == null || tarefa.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da tarefa é obrigatório.");
        }
    }

}
