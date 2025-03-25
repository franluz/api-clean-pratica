package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.ListarAbrigoDTO;
import br.com.alura.adopet.api.enumm.ValidacaoFrases;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {
    @Autowired
    private AbrigoRepository repository;

    public void cadastrar( CadastroAbrigoDto dto) {
        boolean jaExiste = repository.existsByNomeOrEmailOrTelefone(dto.nome(),dto.telefone() ,dto.email());

        if (jaExiste) {
            throw new ValidacaoException(ValidacaoFrases.DADOS_JA_CADASTRADOS_PARA_ABRIGO.toString());
        } else {
            Abrigo abrigo = new Abrigo(dto.nome(), dto.email(), dto.telefone());
            repository.save(abrigo);
        }
    }

    public List<ListarAbrigoDTO> listar() {
        return repository.findAll().stream()
                .map(item -> new
                        ListarAbrigoDTO(item.getNome(),
                        item.getTelefone(),
                        item.getEmail())).toList();
    }


}
