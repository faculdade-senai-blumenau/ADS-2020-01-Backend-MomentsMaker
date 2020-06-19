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
import senai.momentsmaker.entity.ContatoEntity;
import senai.momentsmaker.repository.ContatoRepository;

@RestController
@RequiredArgsConstructor
public class ContatoController {

	@Autowired
	private final ContatoRepository contatoRepository;

	@GetMapping("/contato")
	public List<ContatoEntity> gellAllContato() {
		return contatoRepository.findAll();
	}

	@GetMapping("/contato/{id}")
	public ContatoEntity getContato(@PathVariable Long id) {
		Optional<ContatoEntity> contato = contatoRepository.findById(id);
		return contato.get();
	}

	@DeleteMapping("/contato/{id}")
	public void deleteContato(@PathVariable Long id) {
		contatoRepository.deleteById(id);
	}

	@PostMapping("/contato")
	public ResponseEntity<Object> createContato(@RequestBody ContatoEntity contato) {
		ContatoEntity savedContato = contatoRepository.save(contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedContato);
	}

	@PutMapping("/contato/{id}")
	public ResponseEntity<Object> updateContato(@RequestBody ContatoEntity contato, @PathVariable Long id) {
		Optional<ContatoEntity> contatoOptional = contatoRepository.findById(id);

		if (!contatoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		contato.setId(id);
		contatoRepository.save(contato);
		return ResponseEntity.noContent().build();
	}
}
