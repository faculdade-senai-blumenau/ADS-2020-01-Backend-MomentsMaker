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

import br.senai.momentsmaker.entity.LoginEntity;
import br.senai.momentsmaker.repository.LoginRepository;
import lombok.RequiredArgsConstructor;

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
