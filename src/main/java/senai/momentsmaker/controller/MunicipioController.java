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
import senai.momentsmaker.entity.MunicipioEntity;
import senai.momentsmaker.repository.MunicipioRepository;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MunicipioController {

	@Autowired
	private final MunicipioRepository municipioRepository;

	@GetMapping("/municipio")
	public List<MunicipioEntity> gellAllMunicipio() {
		return municipioRepository.findAll();
	}

	@GetMapping("/municipio/{id}")
	public MunicipioEntity getMunicipio(@PathVariable Long id) {
		Optional<MunicipioEntity> municipio = municipioRepository.findById(id);
		return municipio.get();
	}

	@DeleteMapping("/municipio/{id}")
	public void deleteMunicipio(@PathVariable Long id) {
		municipioRepository.deleteById(id);
	}

	@PostMapping
	public ResponseEntity<MunicipioEntity> save(@RequestBody MunicipioEntity municipioEntity) {
		MunicipioEntity savedMunicipio = municipioRepository.save(municipioEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMunicipio);
	}

	@PutMapping("/municipio/{id}")
	public ResponseEntity<Object> updateMunicipio(@RequestBody MunicipioEntity municipio, @PathVariable Long id) {
		Optional<MunicipioEntity> municipioOptional = municipioRepository.findById(id);

		if (!municipioOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		municipio.setId(id);
		municipioRepository.save(municipio);
		return ResponseEntity.noContent().build();
	}
}
