package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoPetDisponivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {
    @Mock
    PetRepository petRepository;
    @InjectMocks
    ValidacaoPetDisponivel validacaoPetDisponivel = new ValidacaoPetDisponivel();
    @Mock
    Pet pet;
    SolicitacaoAdocaoDTO dto;

    @BeforeEach
    void init() {
        dto = new SolicitacaoAdocaoDTO(7l, 2l, "Motivo qualquer");
    }

    @Test
    @DisplayName("Devemos conseguir adotar o Pet")
    void deveriaPermitirSolicitacaoDeAdocaoPet() {
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);
        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validacao(dto));
    }

    @Test
    @DisplayName("Deve dar exception")
    void naoDeveriaPermitirSolicitacaoDeAdocaoPet() {
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);
        Assertions.assertThrows(ValidacaoException.class,() -> validacaoPetDisponivel.validacao(dto));
    }
}