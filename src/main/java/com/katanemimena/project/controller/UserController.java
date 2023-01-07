package com.katanemimena.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.katanemimena.project.entity.User;
import com.katanemimena.project.exceptions.UserNotFoundException;
import com.katanemimena.project.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// Get All Users
	@GetMapping("/users")
	public List<User> getAllUsers() {
	    return userRepository.findAll();
	}

	// Get a Single Note
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
	
	// Create a new User
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
	    return userRepository.save(user);
	}

	// Update a User
	@PutMapping("/users/{id}") User updateUser(@RequestBody User newUser, @PathVariable Long id) {
	    return userRepository.findById(id)
	    		.map(user -> {
	    			user.setUsername(newUser.getUsername());
	    			user.setPassword(newUser.getPassword());
	    	        user.setFirstName(newUser.getFirstName());
	    	        user.setLastName(newUser.getLastName());
	    	        user.setEmail(newUser.getEmail());
	    	        return userRepository.save(user);
	    	      })
	    	      .orElseGet(() -> {
	    	        newUser.setId(id);
	    	        return userRepository.save(newUser);
	    	      });
	    	  }

	// Delete a User
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
}
