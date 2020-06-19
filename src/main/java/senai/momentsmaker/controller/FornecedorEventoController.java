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
import senai.momentsmaker.entity.FornecedorEventoEntity;
import senai.momentsmaker.repository.FornecedorEventoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FornecedorEventoController {

	@Autowired
	private final FornecedorEventoRepository fornecedorEventoRepository;

	@GetMapping("/fornecedorEvento")
	public List<FornecedorEventoEntity> gellAllFornecedorEvento() {
		return fornecedorEventoRepository.findAll();
	}

	@GetMapping("/fornecedorEvento/{id}")
	public FornecedorEventoEntity getFornecedorEvento(@PathVariable Long id) {
		Optional<FornecedorEventoEntity> fornecedorEvento = fornecedorEventoRepository.findById(id);
		return fornecedorEvento.get();
	}

	@DeleteMapping("/fornecedorEvento/{id}")
	public void deleteFornecedorEvento(@PathVariable Long id) {
		fornecedorEventoRepository.deleteById(id);
	}

	@PostMapping("/fornecedorEvento")
	public ResponseEntity<Object> createFornecedorEvento(@RequestBody FornecedorEventoEntity fornecedorEvento) {
		FornecedorEventoEntity savedFornecedorEvento = fornecedorEventoRepository.save(fornecedorEvento);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFornecedorEvento);
	}

	@PutMapping("/fornecedorEvento/{id}")
	public ResponseEntity<Object> updateFornecedorEvento(@RequestBody FornecedorEventoEntity fornecedorEvento, @PathVariable Long id) {
		Optional<FornecedorEventoEntity> fornecedorEventoOptional = fornecedorEventoRepository.findById(id);

		if (!fornecedorEventoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		fornecedorEvento.setId(id);
		fornecedorEventoRepository.save(fornecedorEvento);
		return ResponseEntity.noContent().build();
	}
}
