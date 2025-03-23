package br.com.alura.adopet;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdocaoServiceTest {

    AdocaoService adocaoService;
    static SolicitacaoAdocaoDTO dto;
    @BeforeAll
    public static void init(){
            dto =   mock(SolicitacaoAdocaoDTO.class);
    }

}
