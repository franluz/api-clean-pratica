package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    private String nome;

    private String raca;

    private Integer idade;

    private String cor;

    private Float peso;

    private Boolean adotado;

    @ManyToOne(fetch = FetchType.LAZY)

    private Abrigo abrigo;

    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)

    private Adocao adocao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    public Pet() {
        super();
    }

    public Pet(CadastroPetDto dto, Abrigo abrigo) {
        this.tipo = dto.tipo();
        this.nome = dto.nome();
        this.raca = dto.raca();
        this.idade = dto.idade();
        this.cor = dto.cor();
        this.peso = dto.peso();
        this.adotado = false;
        this.abrigo = abrigo;
    }

    public Pet(Abrigo abrigo, Boolean isAdotado, String raca, String cor, String nome) {
        this.abrigo = abrigo;
        this.adotado = isAdotado;
        this.cor = cor;
        this.raca = raca;
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPet getTipo() {
        return tipo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }


    public Integer getIdade() {
        return idade;
    }


    public String getCor() {
        return cor;
    }


    public Float getPeso() {
        return peso;
    }


    public Boolean getAdotado() {
        return adotado;
    }


    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }

    public Adocao getAdocao() {
        return adocao;
    }

    public void setAdocao(Adocao adocao) {
        this.adocao = adocao;
    }
}
