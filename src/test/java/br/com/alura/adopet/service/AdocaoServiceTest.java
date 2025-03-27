package br.com.alura.adopet.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.AdocaoService;
import br.com.alura.adopet.api.service.EmailService;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {
    @Mock
    AdocaoRepository repository;
    @Mock
    PetRepository petRepository;
    @Mock
    TutorRepository tutorRepository;

    @Mock
    Pet pet;
    @Mock
    Tutor tutor;
    @Mock
    Adocao adocao;
    @Mock
    Abrigo abrigo;
    @InjectMocks
    AdocaoService adocaoService;
    SolicitacaoAdocaoDTO dto;
    @Captor
    ArgumentCaptor<Adocao> adocaoArgumentCaptor;
    @Spy
    List<ValidacaoSolicitacaoAdocao> validacaoSolicitacaoAdocao =
            mock(ArrayList.class, withSettings()
                    .useConstructor().defaultAnswer(CALLS_REAL_METHODS));
    @Mock
    ValidacaoSolicitacaoAdocao validador1;
    @Mock
    ValidacaoSolicitacaoAdocao validador2;

    @Mock
    EmailService emailService;

    @BeforeEach
    void init() {
        dto = new SolicitacaoAdocaoDTO(1l, 2l, "Qualquer");
    }

    @Test
    void deveriaValidarSalvarAdocaoAoSolicitar() {
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);
        validacaoSolicitacaoAdocao.add(validador1);
        validacaoSolicitacaoAdocao.add(validador2);
        adocaoService.solicitar(dto);
        BDDMockito.then(validador1).should().validacao(dto);
        BDDMockito.then(validador2).should().validacao(dto);
    }

    @Test
    @DisplayName("Deveria salvar adocao ao Solicitar")
    void deveriaSalvarAdocaoAoSolicitar() {
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        adocaoService.solicitar(dto);
        then(repository).should().save(adocaoArgumentCaptor.capture());
        Adocao adocaoSalva = adocaoArgumentCaptor.getValue();
        assertEquals(pet, adocaoSalva.getPet());
        assertEquals(tutor, adocaoSalva.getTutor());
        assertEquals(dto.motivo(), adocaoSalva.getMotivo());
    }

}