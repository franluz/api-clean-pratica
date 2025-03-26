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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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
    @Mock
    List<ValidacaoSolicitacaoAdocao> validacaoSolicitacaoAdocao;
    @Mock
    EmailService emailService;

    @BeforeEach
    void init() {
        dto = new SolicitacaoAdocaoDTO(1l, 2l, "Qualquer");
    }

    @Test
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