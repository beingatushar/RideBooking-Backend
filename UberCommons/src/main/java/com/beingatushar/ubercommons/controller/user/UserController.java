package com.beingatushar.ubercommons.controller.user;

import com.beingatushar.ubercommons.dto.UserDTO;
import com.beingatushar.ubercommons.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

}
