package senai.momentsmaker.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import senai.momentsmaker.entity.PaisEntity;
import senai.momentsmaker.repository.PaisRepository;

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
