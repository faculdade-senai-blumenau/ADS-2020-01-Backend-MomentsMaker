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
import senai.momentsmaker.entity.CategoriaEntity;
import senai.momentsmaker.repository.CategoriaRepository;

@RestController
@RequiredArgsConstructor
public class CategoriaController {

	@Autowired
	private final CategoriaRepository categoriaRepository;

	@GetMapping("/categorias")
	public List<CategoriaEntity> gellAllCategoria() {
		return categoriaRepository.findAll();
	}

	@GetMapping("/categorias/{id}")
	public CategoriaEntity getCategoria(@PathVariable Long id) {
		Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);
		return categoria.get();
	}

	@DeleteMapping("/categorias/{id}")
	public void deleteCategoria(@PathVariable Long id) {
		categoriaRepository.deleteById(id);
	}

	@PostMapping("/categorias")
	public ResponseEntity<Object> createCategoria(@RequestBody CategoriaEntity categoria) {
		CategoriaEntity savedCategoria = categoriaRepository.save(categoria);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCategoria.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/categorias/{id}")
	public ResponseEntity<Object> updateCategoria(@RequestBody CategoriaEntity categoria, @PathVariable Long id) {
		Optional<CategoriaEntity> categoriaOptional = categoriaRepository.findById(id);

		if (!categoriaOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		categoria.setId(id);
		categoriaRepository.save(categoria);
		return ResponseEntity.noContent().build();
	}
}