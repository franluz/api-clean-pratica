package br.com.alura.adopet.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validacoes.TutorComLimiteAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TutorComLimiteAdocaoTest {

    @Mock
    AdocaoRepository repository;
    @Mock
    Adocao adocao;
    List<Optional<Adocao>> adocoes = new ArrayList<Optional<Adocao>>();

    @InjectMocks
    TutorComLimiteAdocao tutorComLimiteAdocao = new TutorComLimiteAdocao();
    SolicitacaoAdocaoDTO dto;

    @BeforeEach
    void init() {
        dto = new SolicitacaoAdocaoDTO(1l, 2l, "Qualquer");
        adocoes.add(Optional.of(adocao));
        adocoes.add(Optional.of(adocao));
        adocoes.add(Optional.of(adocao));
        adocoes.add(Optional.of(adocao));
        adocoes.add(Optional.of(adocao));


    }

    @Test
    @DisplayName("Deveria deixar adotar")
    void tutorComLimite() {
        adocoes = new ArrayList<Optional<Adocao>>();
        BDDMockito.given(repository.
                        findAllByIdTutor(dto.idTutor()))
                .willReturn(adocoes);
        Assertions.assertDoesNotThrow(
                () -> this.tutorComLimiteAdocao.validacao(dto));
    }

    @Test
    @DisplayName("Nao deveria deixar adotar")
    void tutorNoLimite() {
        BDDMockito.given(repository.
                        findAllByIdTutor(dto.idTutor()))
                .willReturn(adocoes);
        Assertions.assertThrows(ValidacaoException.class,
                () -> this.tutorComLimiteAdocao.validacao(dto));
    }


}