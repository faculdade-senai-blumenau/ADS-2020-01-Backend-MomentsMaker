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
import senai.momentsmaker.entity.FornecedorEntity;
import senai.momentsmaker.repository.FornecedorRepository;

@RestController
@RequiredArgsConstructor
public class FornecedorController {

	@Autowired
	private final FornecedorRepository fornecedorRepository;

	// Traz todos fornecedores
	@GetMapping("/fornecedores")
	public List<FornecedorEntity> gellAllFornecedores() {
		return fornecedorRepository.findAll();
	}

	// Traz detalhes de um fornecedor em específico
	@GetMapping("/fornecedores/{id}")
	public FornecedorEntity getFornecedor(@PathVariable Long id) {
		// Optional recurso java 8 mais informações https://medium.com/@racc.costa/optional-no-java-8-e-no-java-9-7c52c4b797f1
		Optional<FornecedorEntity> fornecedor = fornecedorRepository.findById(id);
		return fornecedor.get();
	}
	
	// Deletar um usuário por id
	@DeleteMapping("/fornecedores/{id}")
	public void deleteFornecedor(@PathVariable Long id) {
		fornecedorRepository.deleteById(id);
	}
	
	@PostMapping("/fornecedores")
	public ResponseEntity<Object> createFornecedor(@RequestBody FornecedorEntity fornecedor) {
		FornecedorEntity savedFornecedor = fornecedorRepository.save(fornecedor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedFornecedor.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	// Atualizar Fornecedor Existente
	@PutMapping("/fornecedores/{id}")
	public ResponseEntity<Object> updateFornecedor(@RequestBody FornecedorEntity fornecedor, @PathVariable Long id) {
		Optional<FornecedorEntity> fornecedorOptional = fornecedorRepository.findById(id);

		if (!fornecedorOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		fornecedor.setId(id);
		fornecedorRepository.save(fornecedor);
		return ResponseEntity.noContent().build();
	}
}
