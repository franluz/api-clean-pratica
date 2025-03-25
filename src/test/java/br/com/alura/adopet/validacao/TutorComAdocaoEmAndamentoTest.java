package br.com.alura.adopet.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validacoes.TutorComAdocaoEmAndamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class TutorComAdocaoEmAndamentoTest {

    @Mock
    AdocaoRepository repository;

    SolicitacaoAdocaoDTO dto;
    @InjectMocks
    TutorComAdocaoEmAndamento tutorComAdocaoEmAndamento = new TutorComAdocaoEmAndamento();

    @BeforeEach
    void init() {
        this.dto = new SolicitacaoAdocaoDTO(1l, 2l, "Motivo");
    }
    @Test
    @DisplayName("Tutor nao tem adocao em andamento")
    void tutorNaoPossuiAdocaoEmAndamentovalidacao() {

        BDDMockito.given(repository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);
        Assertions.assertDoesNotThrow( () -> tutorComAdocaoEmAndamento.validacao(dto));
    }
    @Test
    @DisplayName("Tutor ja tem adocao em andamento")
    void tutorPossuiAdocaoEmAndamentovalidacao() {

        BDDMockito.given(repository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);
        Assertions.assertThrows(ValidacaoException.class, () -> tutorComAdocaoEmAndamento.validacao(dto));
    }
}