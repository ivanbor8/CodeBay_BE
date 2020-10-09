package com.init.users.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.users.dao.UsersDAO;
import com.init.users.entities.User;

@RestController
@RequestMapping("users")
public class UsersREST {

	@Autowired
	private UsersDAO userDAO;

	// Get Full Users List
	@GetMapping
	public ResponseEntity<List<User>> getUser() {

		List<User> users = userDAO.findAll();

		return ResponseEntity.ok(users);
	}

	// Get specific User
	@RequestMapping(value = "{userID}")
	public ResponseEntity<User> getUserById(@PathVariable("userID") Long userID) {

		Optional<User> optionalUser = userDAO.findById(userID);

		if (optionalUser.isPresent()) {

			return ResponseEntity.ok(optionalUser.get());

		} else {

			return ResponseEntity.noContent().build();
		}

	}

	// Create a user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User newUser = userDAO.save(user);

		return ResponseEntity.ok(newUser);

	}

}
