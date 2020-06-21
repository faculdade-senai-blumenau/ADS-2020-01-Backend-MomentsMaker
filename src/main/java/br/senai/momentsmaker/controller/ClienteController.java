package br.senai.momentsmaker.controller;

import java.util.List;
import java.util.Optional;

import br.senai.momentsmaker.entity.ApiResponse;
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

import br.senai.momentsmaker.entity.ClienteEntity;
import br.senai.momentsmaker.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ClienteController {

	@Autowired
	private final ClienteRepository clienteRepository;

	@GetMapping("/cliente")
	public List<ClienteEntity> gellAllContato() {
		return clienteRepository.findAll();
	}

	@GetMapping("/cliente/{id}")
	public ClienteEntity getCliente(@PathVariable Long id) {
		Optional<ClienteEntity> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@DeleteMapping("/cliente/{id}")
	public void deleteCliente(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}

	@PostMapping("/cliente")
	public ResponseEntity<ClienteEntity> save(@RequestBody ClienteEntity clienteEntity) {
		ClienteEntity savedCliente = clienteRepository.save(clienteEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
	}

	@PutMapping("/cliente")
	public ApiResponse<ClienteEntity> update(@RequestBody ClienteEntity clienteEntity) {
		Optional<ClienteEntity> cliente = clienteRepository.findById(clienteEntity.getId());

		if (cliente.isPresent()) {
			clienteRepository.save(clienteEntity);
			return new ApiResponse<>(HttpStatus.OK.value(), "Dados do cliente atualizados com sucesso.", cliente);
		}

		return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Não foi possível atualizar os dados do cliente.", cliente);
	}
}
