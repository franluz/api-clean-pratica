package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record PetDTO(
        @NotBlank String nome,
        @NotBlank String raca,
        @NotBlank String cor) {
}
