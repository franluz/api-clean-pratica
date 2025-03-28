package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record ListarAbrigoDTO(
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank String email) {
}
