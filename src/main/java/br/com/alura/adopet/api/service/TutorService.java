package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroTutorDTO;
import br.com.alura.adopet.api.enumm.ValidacaoFrases;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(CadastroTutorDTO dto) {
        boolean jaCadastrado = repository.existsByTelefoneOrEmail(dto.telefone(),dto.email());
        Tutor tutor = new Tutor(dto.nome(),dto.email(),dto.telefone());
         if (jaCadastrado ) {
            throw new ValidacaoException(ValidacaoFrases.DADOS_JA_CADASTRADOS_PARA_TUTOR.toString());
        } else {
            repository.save(tutor);
        }
    }

}
