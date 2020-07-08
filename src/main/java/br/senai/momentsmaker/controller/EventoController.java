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

import br.senai.momentsmaker.entity.EventoEntity;
import br.senai.momentsmaker.repository.EventoRepository;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
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
		eventoRepository.inserirEventoFornecedor(savedEvento.getId(), evento.getIdFornecedor());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
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

	@GetMapping("/evento/cliente/{id}")
	public List<EventoEntity> getEventosCliente(@PathVariable Long clienteId) {
		return eventoRepository.buscaEventosCliente(clienteId);
	}
}
