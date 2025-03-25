package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "abrigos")

public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
    private String telefone;

    @Email
    private String email;

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("abrigo_pets")
    private List<Pet> pets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abrigo abrigo = (Abrigo) o;
        return Objects.equals(id, abrigo.id);
    }

    public Abrigo() {
        super();
    }
    public Abrigo(CadastroAbrigoDto dto){
        this.nome=dto.nome();
        this.telefone=dto.telefone();
        this.email=dto.email();

    }

    public Abrigo(String nome, String email, String telefone) {
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public String getTelefone() {
        return telefone;
    }


    public String getEmail() {
        return email;
    }


    public List<Pet> getPets() {
        return pets;
    }

}
