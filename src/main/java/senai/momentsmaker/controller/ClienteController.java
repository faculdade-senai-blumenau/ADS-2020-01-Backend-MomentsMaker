package senai.momentsmaker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import senai.momentsmaker.entity.ApiResponse;
import senai.momentsmaker.entity.ClienteEntity;
import senai.momentsmaker.repository.ClienteRepository;

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

	@PutMapping("/cliente/{id}")
	public ApiResponse<ClienteEntity> update(@RequestBody ClienteEntity clienteEntity) {
		Optional<ClienteEntity> cliente = clienteRepository.findById(clienteEntity.getId());

		if (cliente.isPresent()) {
			BeanUtils.copyProperties(clienteEntity, cliente);
			clienteRepository.save(cliente.get());
			return new ApiResponse<>(HttpStatus.OK.value(), "Dados do cliente atualizados com sucesso.", cliente);
		}
		return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Não foi possível atualizar os dados do cliente.", cliente);
	}
}
