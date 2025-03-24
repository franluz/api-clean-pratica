package br.com.alura.adopet.controller;

import br.com.alura.adopet.api.AdopetApiApplication;
import br.com.alura.adopet.api.controller.AdocaoController;
import br.com.alura.adopet.api.service.AdocaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AdopetApiApplication.class)
public class AdocaoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private AdocaoService adocaoService;
    @InjectMocks
    private AdocaoController controller;
    private String SOLICITACAO_URL = "/adocoes";

    @BeforeEach
    public void init() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testSolicitarAdocao() throws Exception {
     when(adocaoService).wait();
        mockMvc.perform(MockMvcRequestBuilders
                        .post(SOLICITACAO_URL)
                         .param("id_pet", "1L")
                        .param("id_tutor", "1L")
                        .param("motivo", "teste")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ;
    }
}
