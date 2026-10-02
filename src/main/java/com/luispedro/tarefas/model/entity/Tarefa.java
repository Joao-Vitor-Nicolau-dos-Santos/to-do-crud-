package com.luispedro.tarefas.model.entity;

import java.time.LocalDateTime;

import com.luispedro.tarefas.model.enums.StatusTarefa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "tarefa", schema = "tarefas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();
        if (this.dataCriacao == null) {
            this.dataCriacao = agora;
        }
        this.dataAtualizacao = agora;
        if (this.status == null) {
            this.status = StatusTarefa.PENDENTE;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
