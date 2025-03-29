package br.com.alura.adopet.controller;

import br.com.alura.adopet.api.AdopetApiApplication;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(
        classes = AdopetApiApplication.class)
class AbrigoControllerTest {
    String URL_BASE = "/abrigos";
    String PEGAR_PET_ID = "/{idOuNome}/pets";
    @Mock
    private AbrigoService service;
    @Mock
    private PetService petService;
    @Mock
    AbrigoRepository repositor;
    CadastroAbrigoDto cadastroAbrigoDto;
    CadastroPetDto cadastroPetDto;
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();


    @BeforeEach
    void init() {
        this.cadastroAbrigoDto = new CadastroAbrigoDto(
                gerarNome(),
                gerarTelefone(),
                gerarEmail());
        this.cadastroPetDto = new CadastroPetDto(
                TipoPet.GATO,
                gerarNome() + "FOFINHO",
                "bible",
                10,
                "Pardo",
                34.3f);
    }
    @Test
    public void naoDeveAadastrarPet() throws Exception {
        String url = URL_BASE + PEGAR_PET_ID.replace("{idOuNome}", "2");
        String param = objectWriter.writeValueAsString(cadastroPetDto);

        var respose = mockMvc.perform(post(url)

                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        Assertions.assertEquals(400, respose.getStatus());

    }
    @Test
    public void cadastrarPet() throws Exception {
        String url = URL_BASE + PEGAR_PET_ID.replace("{idOuNome}", "2");
        String param = objectWriter.writeValueAsString(cadastroPetDto);

        var respose = mockMvc.perform(post(url)
                .content(param)
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        Assertions.assertEquals(200, respose.getStatus());

    }

    @Test
    public void listarPets() throws Exception {
        String url = PEGAR_PET_ID.replace("{idOuNome}", "1");
        var respose = mockMvc.perform(get(URL_BASE + url)

        ).andReturn().getResponse();
        Assertions.assertEquals(200, respose.getStatus());
    }

    @Test
    @DisplayName("Deveria cadastrar o abrigo")
    void cadastrarAbrigo() throws Exception {
        String param = objectWriter.writeValueAsString(cadastroAbrigoDto);
        var respose = mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(param)).andReturn().getResponse();
        Assertions.assertEquals(200, respose.getStatus());
    }

    @Test
    @DisplayName("Nao deveria cadastrar o abrigo")
    void naoCadastrarAbrigo() throws Exception {
        var respose = mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andReturn().getResponse();
        Assertions.assertEquals(400, respose.getStatus());
    }

    @Test
    @DisplayName("Deve ter erro na validacao cadastrar o abrigo")
    void validarCadastrarAbrigo() throws Exception {
        cadastroAbrigoDto = new CadastroAbrigoDto("Frederico", "email@email.com", "3299271102");
        String param = objectWriter.writeValueAsString(cadastroAbrigoDto);
        BDDMockito.given(repositor.existsByNomeOrEmailOrTelefone(cadastroAbrigoDto.nome(),
                cadastroAbrigoDto.telefone(),
                cadastroAbrigoDto.email())).willReturn(true);
        var respose = mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(param)).andReturn().getResponse();
        Assertions.assertEquals(400, respose.getStatus());
    }


    String gerarNome() {
        String[] primeirosNomes = {
                "Ana", "Carlos", "Beatriz", "Eduardo", "Fernanda", "João", "Juliana", "Marcelo", "Ricardo", "Vanessa"
        };

        String[] sobrenomes = {
                "Silva", "Santos", "Oliveira", "Costa", "Pereira", "Rodrigues", "Almeida", "Souza", "Mendes", "Gomes"
        };

        Random random = new Random();

        String primeiroNome = primeirosNomes[random.nextInt(primeirosNomes.length)];
        String sobrenome = sobrenomes[random.nextInt(sobrenomes.length)];
        return primeiroNome + sobrenome;
    }

    String gerarTelefone() {
        Random random = new Random();
        int ddd = random.nextInt(90) + 11;
        StringBuilder telefone = new StringBuilder();
        telefone.append("9");
        for (int i = 0; i < 8; i++) {
            telefone.append(random.nextInt(10));
        }
        return ddd + telefone.toString();
    }

    String gerarEmail() {
        String[] dominios = {
                "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "live.com", "aol.com"
        };
        String nomeDeUsuario = gerarNome();
        Random random = new Random();
        String dominio = dominios[random.nextInt(dominios.length)];
        return nomeDeUsuario + "@" + dominio;
    }
}