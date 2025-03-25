package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroTutorDTO(
        @NotBlank  String nome,
        @NotBlank String email,
        @NotBlank String telefone) {
}
