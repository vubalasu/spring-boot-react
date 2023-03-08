package com.bezkoder.integrate.spring.react.controller;

import com.bezkoder.integrate.spring.react.model.User;
import com.bezkoder.integrate.spring.react.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;
	

	@PostMapping("/users")
	public ResponseEntity<User> createTutorial(@Valid @RequestBody User user) {
		try {
			User _user = userRepository
					.save(new User(user.getName(), user.getSex(), user.getAge(),user.getCountry()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

}
