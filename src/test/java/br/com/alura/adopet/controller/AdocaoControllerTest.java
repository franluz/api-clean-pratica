package br.com.alura.adopet.controller;

import br.com.alura.adopet.api.AdopetApiApplication;
import br.com.alura.adopet.api.controller.AdocaoController;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.service.AdocaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(
         classes = AdopetApiApplication.class)
public class AdocaoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @MockBean
    AdocaoService adocaoService;

    @InjectMocks
    private AdocaoController adocaoController;
    private String SOLICITACAO_URL = "/adocoes";

    @BeforeEach
    public void init() {

    }

    @Test
    void deveriaDevolverCodigo400ParaSOlicitacaoDeadocaoComErros() {

    }

    @Test
    public void testSolicitarAdocao() throws Exception {
        //BD(balance).when(adocaoService).ca(accountId);

        var response = mockMvc.perform(
                        post(SOLICITACAO_URL)
                                .content("{}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());

    }
}
