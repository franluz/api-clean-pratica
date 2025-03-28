package br.com.alura.adopet.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
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
    TutorRepository tutorRepository;
    @Mock
    Adocao adocao;

    Optional<Tutor> opttutor;
    Tutor tutor;
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
        tutor = new Tutor("Chico","chico@teste.com.br","12345678");
        opttutor = Optional.of(tutor);
    }

    @Test
    @DisplayName("Deveria deixar adotar")
    void tutorComLimite() {
        BDDMockito.given(tutorRepository.findById(dto.idTutor())).willReturn(opttutor);
        adocoes = new ArrayList<Optional<Adocao>>();
        BDDMockito.given(repository.
                        findAllByTutor(tutor))
                .willReturn(adocoes);
        Assertions.assertDoesNotThrow(
                () -> this.tutorComLimiteAdocao.validacao(dto));
    }

    @Test
    @DisplayName("Nao deveria deixar adotar")
    void tutorNoLimite() {
        BDDMockito.given(tutorRepository.findById(dto.idTutor())).willReturn(opttutor);
        BDDMockito.given(repository.
                        findAllByTutor(tutor))
                .willReturn(adocoes);
        Assertions.assertThrows(ValidacaoException.class,
                () -> this.tutorComLimiteAdocao.validacao(dto));
    }


}