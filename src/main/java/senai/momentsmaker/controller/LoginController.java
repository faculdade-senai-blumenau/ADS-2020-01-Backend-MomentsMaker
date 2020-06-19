package senai.momentsmaker.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import senai.momentsmaker.entity.LoginEntity;
import senai.momentsmaker.repository.LoginRepository;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoginController {

	@Autowired
	private final LoginRepository loginRepository;

	@GetMapping("/login")
	public List<LoginEntity> gellAllLogin() {
		return loginRepository.findAll();
	}

	@GetMapping("/login/{id}")
	public LoginEntity getLogin(@PathVariable Long id) {
		Optional<LoginEntity> login = loginRepository.findById(id);
		return login.get();
	}

	@DeleteMapping("/login/{id}")
	public void deleteLogin(@PathVariable Long id) {
		loginRepository.deleteById(id);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginEntity> save(@RequestBody LoginEntity loginEntity) {
		LoginEntity savedLogin = loginRepository.save(loginEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLogin);
	}

	@PutMapping("/login/{id}")
	public ResponseEntity<Object> updateLogin(@RequestBody LoginEntity login, @PathVariable Long id) {
		Optional<LoginEntity> LoginOptional = loginRepository.findById(id);

		if (!LoginOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		login.setId(id);
		loginRepository.save(login);
		return ResponseEntity.noContent().build();
	}
}
