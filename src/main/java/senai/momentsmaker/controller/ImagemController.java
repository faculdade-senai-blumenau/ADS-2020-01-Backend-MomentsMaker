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
import senai.momentsmaker.entity.ImagemEntity;
import senai.momentsmaker.repository.ImagemRepository;

@RestController
@RequiredArgsConstructor
public class ImagemController {

	@Autowired
	private final ImagemRepository imagemRepository;

	@GetMapping("/imagem")
	public List<ImagemEntity> gellAllImagem() {
		return imagemRepository.findAll();
	}

	@GetMapping("/imagem/{id}")
	public ImagemEntity getImagem(@PathVariable Long id) {
		Optional<ImagemEntity> imagem = imagemRepository.findById(id);
		return imagem.get();
	}

	@DeleteMapping("/imagem/{id}")
	public void deleteImagem(@PathVariable Long id) {
		imagemRepository.deleteById(id);
	}

	@PostMapping("/imagem")
	public ResponseEntity<Object> createImagem(@RequestBody ImagemEntity imagem) {
		ImagemEntity savedImagem = imagemRepository.save(imagem);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedImagem);
	}

	@PutMapping("/imagem/{id}")
	public ResponseEntity<Object> updateImagem(@RequestBody ImagemEntity imagem, @PathVariable Long id) {
		Optional<ImagemEntity> imagemOptional = imagemRepository.findById(id);

		if (!imagemOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		imagem.setId(id);
		imagemRepository.save(imagem);
		return ResponseEntity.noContent().build();
	}
}
