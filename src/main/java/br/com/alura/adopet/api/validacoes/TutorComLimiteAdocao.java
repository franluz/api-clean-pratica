package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.enumm.ValidacaoFrases;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TutorComLimiteAdocao implements ValidacaoSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository repository;
    @Autowired
    private TutorRepository tutorRepository;


    private Integer MAXIMO_PERMITIDO = 5;

    public void validacao(SolicitacaoAdocaoDTO dto) {
        Optional<Tutor> tutor = tutorRepository.findById(dto.idTutor());
        if (tutor.isPresent()) {
            List<Optional<Adocao>> adocoes = repository.findAllByTutor(tutor.get());
            if (adocoes.stream().count() == MAXIMO_PERMITIDO) {
                throw new ValidacaoException(ValidacaoFrases.TUTOR_MAXIMO_PERMITIDO.toString());
            }
        }
    }
}
