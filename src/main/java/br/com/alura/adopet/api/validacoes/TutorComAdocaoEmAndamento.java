package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.enumm.ValidacaoFrases;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository repository;


    public void validacao(SolicitacaoAdocaoDTO dto) {
        boolean isTutorComAdocaoEmAndamento = repository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (isTutorComAdocaoEmAndamento) {
            throw new ValidacaoException(ValidacaoFrases.TUTOR_POSSUI_ADOCAO_EM_ANDAMENTO.toString());
        }
    }

}
