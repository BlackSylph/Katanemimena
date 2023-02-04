package com.katanemimena.project.controller;

import com.katanemimena.project.entity.User;
import com.katanemimena.project.exceptions.UserNotFoundException;
import com.katanemimena.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    // Get All Users
    @GetMapping("")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a Single Note
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    // Create a new User
    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    // Update a User
    @PutMapping("/{id}") User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setEnabled(newUser.isEnabled());
                    user.setRoles(newUser.getRoles());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    // Delete a User
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }
}