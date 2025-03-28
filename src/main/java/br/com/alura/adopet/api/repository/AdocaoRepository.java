package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

      Boolean existsByPetIdAndStatus(Long idPet, StatusAdocao statusAdocao);
      boolean existsByTutorIdAndStatus(Long idTutod, StatusAdocao statusAdocao);
      List<Optional<Adocao>> findAllByTutor(Tutor tutor);

}
