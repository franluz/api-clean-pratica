package br.com.alura.adopet.controller;

import br.com.alura.adopet.api.AdopetApiApplication;
import br.com.alura.adopet.api.controller.AdocaoController;
import br.com.alura.adopet.api.dto.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.service.AdocaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
    SolicitacaoAdocaoDTO dto;
    AprovacaoAdocaoDTO aprovacaoAdocaoDTO;
    ReprovacaoAdocaoDTO reprovacaoAdocaoDTO;

    @InjectMocks
    private AdocaoController adocaoController;

    private String SOLICITACAO_URL = "/adocoes";
    private String APROVACAOES_URL = "/aprovar";
    private String REPROVAR_URL = "/reprovar";

    @BeforeEach
    public void init() {
        dto = new SolicitacaoAdocaoDTO(1l, 2l, "Motivo teste");
        aprovacaoAdocaoDTO = new AprovacaoAdocaoDTO(1l);
        reprovacaoAdocaoDTO = new ReprovacaoAdocaoDTO(1l,"Esse gato eu vou levar pra casa");
    }

    @Test
    @DisplayName("Reprovar solicitacao por id")
    void deveriaReprovarASolicitacao() throws Exception {
        String param = objectWriter.writeValueAsString(reprovacaoAdocaoDTO);
        var response = mockMvc.perform(put(SOLICITACAO_URL
                        + "/" +
                        REPROVAR_URL)
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Aprovar solicitacao por id")
    void deveriaAprovarASolicitacao() throws Exception {
        String param = objectWriter.writeValueAsString(aprovacaoAdocaoDTO);
        var response = mockMvc.perform(put(SOLICITACAO_URL
                        + "/" +
                        APROVACAOES_URL)
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("nao aprovar solicitacao sem id")
    void naoDeveriaAprovarASolicitacao() throws Exception {
        var response = mockMvc.perform(put(SOLICITACAO_URL
                        + "/" +
                        APROVACAOES_URL)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Deveria fazer a solicitacao com sucesso")
    void deveriaDevolverCodigo200ParaSolicitacaoDeadocao() throws Exception {
        String param = objectWriter.writeValueAsString(dto);
        var response = mockMvc.perform(
                        post(SOLICITACAO_URL)
                                .content(param)
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

    }

    @Test
    @DisplayName("Deveria devolver codigo de erro pois o json esta vazio")
    public void deveriaDevolverCodigo400ParaSOlicitacaoDeadocaoComErros() throws Exception {

        var response = mockMvc.perform(
                        post(SOLICITACAO_URL)
                                .content("{}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());

    }
}
