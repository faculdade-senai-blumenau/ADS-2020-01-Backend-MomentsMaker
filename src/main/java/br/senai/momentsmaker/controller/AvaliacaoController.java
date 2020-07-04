package br.senai.momentsmaker.controller;

import java.util.List;
import java.util.Optional;

import br.senai.momentsmaker.repository.ClienteRepository;
import br.senai.momentsmaker.repository.FornecedorRepository;

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

import br.senai.momentsmaker.entity.AvaliacaoEntity;

import br.senai.momentsmaker.repository.AvaliacaoRepository;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AvaliacaoController {
	
	@Autowired
	private final AvaliacaoRepository avaliacaoRepository;

	@Autowired
	private final FornecedorRepository fornecedorRepository;

	@Autowired
	private final ClienteRepository clienteRepository;

	@GetMapping("/avaliacao")
	public List<AvaliacaoEntity> gellAllAvaliacao() {
		return avaliacaoRepository.findAll();
	}

	@GetMapping("/avaliacao/{id}")
	public AvaliacaoEntity getAvaliacao(@PathVariable Long id) {
		Optional<AvaliacaoEntity> avaliacao = avaliacaoRepository.findById(id);
		return avaliacao.get();
	}

	@DeleteMapping("/avaliacao/{id}")
	public void deleteAvaliacao(@PathVariable Long id) {
		avaliacaoRepository.deleteById(id);
	}

	@Transactional
	@PostMapping("/avaliacao")
	public ResponseEntity<Object> createAvaliacao(@RequestBody AvaliacaoEntity avaliacao) {
		AvaliacaoEntity savedAvaliacao = avaliacaoRepository.save(avaliacao);

		if (avaliacao.getCliente() != null) {
			Double notaMediaAvaliacaoCliente = avaliacaoRepository.notaMediaAvaliacaoCliente(avaliacao.getCliente().getId());
			clienteRepository.persistirNotaMediaAvaliacao(notaMediaAvaliacaoCliente, avaliacao.getCliente().getId());

			return ResponseEntity.status(HttpStatus.CREATED).body(savedAvaliacao);
		}

		if (avaliacao.getFornecedor() != null) {
			Double notaMediaAvaliacaoFornecedor = avaliacaoRepository.notaMediaAvaliacaoFornecedor(avaliacao.getFornecedor().getId());
			fornecedorRepository.persistirNotaMediaAvaliacao(notaMediaAvaliacaoFornecedor, avaliacao.getFornecedor().getId());

			return ResponseEntity.status(HttpStatus.CREATED).body(savedAvaliacao);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(avaliacao);

	}

	@PutMapping("/avaliacao/{id}")
	public ResponseEntity<Object> updateAvaliacao(@RequestBody AvaliacaoEntity avaliacao, @PathVariable Long id) {
		Optional<AvaliacaoEntity> avaliacaoOptional = avaliacaoRepository.findById(id);

		if (!avaliacaoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		avaliacao.setId(id);
		avaliacaoRepository.save(avaliacao);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/avaliacao/fornecedor/{fornecedorId}")
	public Double getNotaMediaFornecedor(@PathVariable Long fornecedorId) {
		return avaliacaoRepository.notaMediaAvaliacaoFornecedor(fornecedorId);
	}

	@GetMapping("/avaliacao/cliente/{clienteId}")
	public Double getNotaMediaCliente(@PathVariable Long clienteId) {
		return avaliacaoRepository.notaMediaAvaliacaoCliente(clienteId);
	}
}

