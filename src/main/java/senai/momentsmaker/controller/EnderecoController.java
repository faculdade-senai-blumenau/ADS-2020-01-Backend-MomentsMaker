package senai.momentsmaker.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import senai.momentsmaker.entity.EnderecoEntity;
import senai.momentsmaker.repository.EnderecoRepository;

@RestController
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
	public ResponseEntity<Object> createEndereco(@RequestBody EnderecoEntity endereco) {
		EnderecoEntity savedEndereco = enderecoRepository.save(endereco);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEndereco.getId()).toUri();
		return ResponseEntity.created(location).build();
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
