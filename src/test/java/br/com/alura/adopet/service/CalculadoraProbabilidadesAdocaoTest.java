package br.com.alura.adopet.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.service.CalculadoraProbabilidadeAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraProbabilidadesAdocaoTest {
    Pet pet;
    Pet pet1;
    Abrigo abrigo;

    @BeforeEach
    void init() {

        abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));
        pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                4,
                "Cinza",
                4.0f
        ), abrigo);
        pet1 = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                15,
                "Cinza",
                4.0f
        ), abrigo);
    }

    @Test
    void gatoComQuatroSAnosEmaisDeQuatroQuilos() {
        CalculadoraProbabilidadeAdocao calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet);
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidadeAdocao);
    }
    @Test
    void gatoComMaisDeDezAnosEMaisDeQuatroQuilos(){
        CalculadoraProbabilidadeAdocao calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet1);
        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidadeAdocao);
    }
}
