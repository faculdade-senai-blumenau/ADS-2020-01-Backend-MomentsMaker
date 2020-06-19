package senai.momentsmaker.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import senai.momentsmaker.entity.ClienteEntity;
import senai.momentsmaker.repository.ClienteRepository;

@RestController
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

	@PutMapping("/cliente/{id}")
	public ResponseEntity<Object> updateCliente(@RequestBody ClienteEntity cliente, @PathVariable Long id) {
		Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);

		if (!clienteOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(id);
		clienteRepository.save(cliente);
		return ResponseEntity.noContent().build();
	}
}
