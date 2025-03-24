package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroTutorDTO;
import br.com.alura.adopet.api.enumm.ValidacaoFrases;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(CadastroTutorDTO dto) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());
        Tutor tutor = repository.getByTelefone(dto.telefone());
        tutor.setEmail(dto.email());
        tutor.setNome(dto.nome());
        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException(ValidacaoFrases.DADOS_JA_CADASTRADOS_PARA_TUTOR.toString());
        } else {
            repository.save(tutor);
        }
    }

}
