package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {
    @Autowired
    private AbrigoRepository repository;
    @Autowired
    private PetRepository petRepository;
    public List<PetDTO> listarPets(String idOuNome) {
        List<Pet> pets = new ArrayList<Pet>();
        try {
            Long id = Long.parseLong(idOuNome);
            pets = repository.getReferenceById(id).getPets();

        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException(enfe.getMessage());
        } catch (NumberFormatException e) {
            try {
                pets = repository.findByNome(idOuNome).getPets();
            } catch (EntityNotFoundException enfe) {
                throw new ValidacaoException(enfe.getMessage());
            }
        }
        return pets.stream().map(item -> new PetDTO(item.getNome(), item.getRaca(),
                item.getCor())).toList();
    }

    private Abrigo carregaAbrigoPet(String idOuNome, CadastroPetDto dto) {
        Abrigo abrigo = null;
        try {
            Long id = Long.parseLong(idOuNome);
            abrigo = repository.getReferenceById(id);
            if (abrigo.getId() == null) {
                abrigo = repository.findByNome(idOuNome);
            }
            Pet pet = new Pet(dto,abrigo);
            abrigo.getPets().add(pet);
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException(enfe.getMessage());
        }
        return abrigo;
    }

    public void cadastrar(String idOuNome, CadastroPetDto dto) {

        try {
            repository.save(carregaAbrigoPet(idOuNome, dto));
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException(enfe.getMessage());
        }
    }
    public List<Pet> listarPetNaoAdotado(){
        return petRepository.findAllByAdotado(false);
    }
}
