package br.com.alura.adopet.service;

import br.com.alura.adopet.api.dto.CadastroTutorDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
public class TutorServiceTest {

    @Mock
    private TutorRepository repository;
    CadastroTutorDTO dto;

    @InjectMocks
    TutorService tutorService;

    @BeforeEach
    void init() {
        this.dto = new CadastroTutorDTO("Tutor1", "email@email.com", "4199999999");
    }

    @Test
    @DisplayName("Deve exibir mensagem de ja cadastrado")
    void deveExibirMensagemJaCadastrado() {
        given(repository.existsByTelefoneOrEmail(dto.telefone(), dto.email())).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> tutorService.cadastrar(dto));
    }
    @Test
    @DisplayName("Deve permitir cadastrar")
    void devePermitirCadastro(){
        given(repository.existsByTelefoneOrEmail(dto.telefone(),dto.email())).willReturn(false);
        Assertions.assertDoesNotThrow(()->this.tutorService.cadastrar(dto));
    }
}
