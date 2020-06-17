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
import senai.momentsmaker.entity.EventoEntity;
import senai.momentsmaker.repository.EventoRepository;

@RestController
@RequiredArgsConstructor
public class EventoController {

	@Autowired
	private final EventoRepository eventoRepository;

	@GetMapping("/evento")
	public List<EventoEntity> gellAllEvento() {
		return eventoRepository.findAll();
	}

	@GetMapping("/evento/{id}")
	public EventoEntity getEvento(@PathVariable Long id) {
		Optional<EventoEntity> evento = eventoRepository.findById(id);
		return evento.get();
	}

	@DeleteMapping("/evento/{id}")
	public void deleteEvento(@PathVariable Long id) {
		eventoRepository.deleteById(id);
	}

	@PostMapping("/evento")
	public ResponseEntity<Object> createEvento(@RequestBody EventoEntity evento) {
		EventoEntity savedEvento = eventoRepository.save(evento);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEvento.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/evento/{id}")
	public ResponseEntity<Object> updateEvento(@RequestBody EventoEntity evento, @PathVariable Long id) {
		Optional<EventoEntity> eventoOptional = eventoRepository.findById(id);

		if (!eventoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		evento.setId(id);
		eventoRepository.save(evento);
		return ResponseEntity.noContent().build();
	}
}
