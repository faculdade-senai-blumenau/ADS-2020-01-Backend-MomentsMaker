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
import senai.momentsmaker.entity.EnderecoEntity;
import senai.momentsmaker.repository.EnderecoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EnderecoController {

	@Autowired
	private final EnderecoRepository enderecoRepository;

	@GetMapping("/endereco")
	public List<EnderecoEntity> gellAllEndereco() {
		return enderecoRepository.findAll();
	}

	@GetMapping("/endereco/{id}")
	public EnderecoEntity getEndereco(@PathVariable Long id) {
		Optional<EnderecoEntity> endereco = enderecoRepository.findById(id);
		return endereco.get();
	}

	@DeleteMapping("/endereco/{id}")
	public void deleteEndereco(@PathVariable Long id) {
		enderecoRepository.deleteById(id);
	}

	@PostMapping("/endereco")
	public ResponseEntity<EnderecoEntity> save(@RequestBody EnderecoEntity enderecoEntity) {
		EnderecoEntity savedEndereco = enderecoRepository.save(enderecoEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEndereco);
	}

	@PutMapping("/endereco/{id}")
	public ResponseEntity<Object> updateEndereco(@RequestBody EnderecoEntity endereco, @PathVariable Long id) {
		Optional<EnderecoEntity> enderecoOptional = enderecoRepository.findById(id);

		if (!enderecoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		endereco.setId(id);
		enderecoRepository.save(endereco);
		return ResponseEntity.noContent().build();
	}
}
