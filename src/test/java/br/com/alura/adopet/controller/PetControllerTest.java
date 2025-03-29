package br.com.alura.adopet.controller;

import br.com.alura.adopet.api.AdopetApiApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest(
        classes = AdopetApiApplication.class)
class PetControllerTest {
    String URL_BASE = "/pets";
    @Autowired
    private MockMvc mockMvc;
    @Test
    public  void listarTodosDisponiveis() throws Exception {
       var response= mockMvc.perform(get(URL_BASE)
               .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        Assertions.assertEquals(200,response.getStatus());
    }

}