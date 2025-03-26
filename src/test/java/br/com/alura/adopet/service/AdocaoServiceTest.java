package br.com.alura.adopet.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.AdocaoService;
import br.com.alura.adopet.api.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;


class AdocaoServiceTest {
    @Mock
    AdocaoRepository repository;
    @Mock
    PetRepository petRepository;
    @Mock
    TutorRepository tutorRepository;
    @Mock
    EmailService emailService;

    @InjectMocks
    AdocaoService adocaoService;
    SolicitacaoAdocaoDTO dto;
    @BeforeEach
    void init(){
        dto = new SolicitacaoAdocaoDTO(1l,2l,"Qualquer");
    }

    @Test
    void deveriaSalvarAdocaoAoSolicitar(){
        adocaoService.solicitar(dto);
        then(petRepository).should().save(any());
    }

}