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
import senai.momentsmaker.entity.LoginEntity;
import senai.momentsmaker.repository.LoginRepository;

@RestController
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
	public ResponseEntity<Object> createLogin(@RequestBody LoginEntity login) {
		LoginEntity savedLogin = loginRepository.save(login);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedLogin.getId()).toUri();
		return ResponseEntity.created(location).build();
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
