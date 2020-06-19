package br.senai.momentsmaker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.senai.momentsmaker.entity.PaisEntity;
import br.senai.momentsmaker.repository.PaisRepository;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PaisController {

	@Autowired
	private final PaisRepository paisRepository;

	@GetMapping("/pais")
	public List<PaisEntity> gellAllPais() {
		return paisRepository.findAll();
	}

	@GetMapping("/pais/{id}")
	public PaisEntity getPais(@PathVariable Long id) {
		Optional<PaisEntity> pais = paisRepository.findById(id);
		return pais.get();
	}

	@DeleteMapping("/pais/{id}")
	public void deletePais(@PathVariable Long id) {
		paisRepository.deleteById(id);
	}

	@PostMapping("/pais")
	public ResponseEntity<PaisEntity> save(@RequestBody PaisEntity paisEntity) {
		PaisEntity savedPais = paisRepository.save(paisEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPais);
	}

	@PutMapping("/pais/{id}")
	public ResponseEntity<Object> updatePais(@RequestBody PaisEntity pais, @PathVariable Long id) {
		Optional<PaisEntity> paisOptional = paisRepository.findById(id);

		if (!paisOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		pais.setId(id);
		paisRepository.save(pais);
		return ResponseEntity.noContent().build();
	}
}
