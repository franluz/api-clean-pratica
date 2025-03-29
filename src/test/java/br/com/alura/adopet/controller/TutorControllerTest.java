package br.com.alura.adopet.controller;

import br.com.alura.adopet.UtilTest;
import br.com.alura.adopet.api.AdopetApiApplication;
import br.com.alura.adopet.api.dto.CadastroTutorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(
        classes = AdopetApiApplication.class)
class TutorControllerTest {
    String URL_BASE = "/tutores";
    @Autowired
    private MockMvc mockMvc;
    CadastroTutorDTO dto;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @BeforeEach
    void init() {
        this.dto = new CadastroTutorDTO(UtilTest.gerarNome(),
                UtilTest.gerarEmail(),
                UtilTest.gerarTelefone());
    }

    @Test
    public void cadastrar() throws Exception {
        String param = objectWriter.writeValueAsString(dto);
       var response= mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(param)
        ).andReturn().getResponse();
        Assertions.assertEquals(200,response.getStatus());

    }

    @Test
 void atualizar() throws Exception {
        String param = objectWriter.writeValueAsString(new CadastroTutorDTO("Ze",
                "email@email",
                "09008999788"));
        var response= mockMvc.perform(put(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(param)
        ).andReturn().getResponse();
        Assertions.assertEquals(200,response.getStatus());

    }
}