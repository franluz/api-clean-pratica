package br.com.alura.adopet.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adocoes")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private String justificativaStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adocao adocao = (Adocao) o;
        return Objects.equals(id, adocao.id);
    }

    public Adocao() {
         super();
    }

    public Adocao(Tutor tutor, Pet pet, String motivo) {
        this.pet = pet;
        this.tutor = tutor;
        this.motivo = motivo;
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
        this.data = LocalDateTime.now();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Pet getPet() {
        return pet;
    }

    public String getMotivo() {
        return motivo;
    }

    public StatusAdocao getStatus() {
        return status;
    }

    public String getJustificativaStatus() {
        return justificativaStatus;
    }

    public void marcarComoAprovado() {
        this.status = StatusAdocao.APROVADO;
    }

    public void marcarComoReprovado(@NotBlank String justificativa) {
        this.status = StatusAdocao.REPROVADO;
        this.motivo = justificativa;
    }
}
