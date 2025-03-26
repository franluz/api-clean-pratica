package br.com.alura.adopet.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoPetComAdocaoEmAndamento;
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
class ValidacaoPetComAdocaoEmAndamentoTest {
    @Mock
    AdocaoRepository adocaoRepository;
    @InjectMocks
    ValidacaoPetComAdocaoEmAndamento validacaoPetComAdocaoEmAndamento = new ValidacaoPetComAdocaoEmAndamento();
    SolicitacaoAdocaoDTO dto;

    @BeforeEach
    void init(){
        dto = new SolicitacaoAdocaoDTO(1l,2l,"Qualquer");
    }
    @Test
    @DisplayName("Pet com adocao em andamento")
    void naoDeveDeixarAdotar() {
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);

       Assertions.assertThrows(ValidacaoException.class,()->
               validacaoPetComAdocaoEmAndamento.validacao(dto));
    }
    @Test
    @DisplayName("Pet sem adocao em andamento")
    void deveDeixarAdotar() {
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);
        Assertions.assertDoesNotThrow(()->
                validacaoPetComAdocaoEmAndamento.validacao(dto));
    }


}