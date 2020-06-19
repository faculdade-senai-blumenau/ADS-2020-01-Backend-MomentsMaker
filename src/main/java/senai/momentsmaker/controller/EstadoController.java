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
import senai.momentsmaker.entity.EstadoEntity;
import senai.momentsmaker.repository.EstadoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstadoController {

	@Autowired
	private final EstadoRepository estadoRepository;

	@GetMapping("/estado")
	public List<EstadoEntity> gellAllEstado() {
		return estadoRepository.findAll();
	}

	@GetMapping("/estado/{id}")
	public EstadoEntity getEstado(@PathVariable Long id) {
		Optional<EstadoEntity> estado = estadoRepository.findById(id);
		return estado.get();
	}

	@DeleteMapping("/estado/{id}")
	public void deleteEstado(@PathVariable Long id) {
		estadoRepository.deleteById(id);
	}

	@PostMapping("/estado")
	public ResponseEntity<EstadoEntity> save(@RequestBody EstadoEntity estadoEntity) {
		EstadoEntity savedEstado = estadoRepository.save(estadoEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEstado);
	}

	@PutMapping("/estado/{id}")
	public ResponseEntity<Object> updateEstado(@RequestBody EstadoEntity estado, @PathVariable Long id) {
		Optional<EstadoEntity> estadoOptional = estadoRepository.findById(id);

		if (!estadoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		estado.setId(id);
		estadoRepository.save(estado);
		return ResponseEntity.noContent().build();
	}
}
