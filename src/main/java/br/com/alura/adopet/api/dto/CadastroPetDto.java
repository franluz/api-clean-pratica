package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;

public record CadastroPetDto(
        @NotBlank TipoPet tipo,
        @NotBlank String nome,
        @NotBlank String raca,
        @NotBlank Integer idade,
        @NotBlank String cor,
        @NotBlank Float peso) {
}
