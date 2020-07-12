package br.senai.momentsmaker.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.senai.momentsmaker.entity.CategoriaEntity;
import br.senai.momentsmaker.repository.CategoriaRepository;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.senai.momentsmaker.entity.FornecedorEntity;
import br.senai.momentsmaker.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FornecedorController {

	private final FornecedorRepository fornecedorRepository;
	private final CategoriaRepository categoriaRepository;

	@GetMapping("/fornecedores")
	public List<FornecedorEntity> get(@RequestParam Optional<String> nomeCategoria) {
		if (nomeCategoria.isPresent()) {
			Optional<CategoriaEntity> categoria = categoriaRepository.findByNome(nomeCategoria.get());
			if (categoria.isPresent()) {
				return fornecedorRepository.findByCategorias(categoria.get());
			} else {
				return Collections.emptyList();
			}
		}
		return fornecedorRepository.findAll();
	}

	@GetMapping("/fornecedores/buscadisponibilidade")
	public List<FornecedorEntity> getDisponibilidade(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
													 @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horaInicio,
													 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim,
													 @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horaFim,
													 @RequestParam Long idCategoria) {

		LocalDateTime dataHoraInicio = LocalDateTime.of(dataInicio, horaInicio);
		LocalDateTime dataHoraFim = LocalDateTime.of(dataFim, horaFim);

		return fornecedorRepository.findByDisponibilidade(dataHoraInicio, dataHoraFim, idCategoria);
	}

	// Traz detalhes de um fornecedor em específico
	@GetMapping("/fornecedores/{id}")
	public FornecedorEntity getFornecedor(@PathVariable Long id) {
		// Optional recurso java 8 mais informações https://medium.com/@racc.costa/optional-no-java-8-e-no-java-9-7c52c4b797f1
		Optional<FornecedorEntity> fornecedor = fornecedorRepository.findById(id);
		return fornecedor.orElse(null);
	}
	
	// Deletar um usuário por id
	@DeleteMapping("/fornecedores/{id}")
	public void deleteFornecedor(@PathVariable Long id) {
		fornecedorRepository.deleteById(id);
	}


	//Verificar o retorno das categorias persistidas(está retornando somente o id)
	@PostMapping("/fornecedores")
	public ResponseEntity<Object> createFornecedor(@RequestBody FornecedorEntity fornecedor) {
		FornecedorEntity savedFornecedor = fornecedorRepository.save(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFornecedor);
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
