package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDTO;
import br.com.alura.adopet.api.dto.CadastrarPetDTO;
import br.com.alura.adopet.api.dto.ListarAbrigoDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.AdocaoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService service;
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<ListarAbrigoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarAbrigoDTO dto) {
         try {
            service.cadastrar(dto);
        } catch (ValidacaoException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Cadastro realizado com sucesso");
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<PetDTO>> listarPets(@PathVariable String idOuNome) {
        return ResponseEntity.ok(this.petService.listarPets(idOuNome));
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastrarPetDTO pet) {
        petService.cadastrar(idOuNome,pet);
        return ResponseEntity.ok("Cadastro realizado com sucesso");

    }

}
