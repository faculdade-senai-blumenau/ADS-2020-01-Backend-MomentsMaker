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

import br.senai.momentsmaker.entity.EnderecoEntity;
import br.senai.momentsmaker.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;

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
