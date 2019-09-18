package com.riscozero.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riscozero.DTO.UserRequestDTO;
import com.riscozero.DTO.UserResponseDTO;
import com.riscozero.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO request) {
		UserResponseDTO response = userService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> find(@PathVariable Long id) {
		UserResponseDTO userDTO = userService.find(id);
		if (userDTO !=null)
			return ResponseEntity.ok(userDTO);
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO request) {
		UserResponseDTO userDTO = userService.update(id, request);
		if (userDTO !=null)
			return ResponseEntity.ok(userDTO);
		
		return ResponseEntity.notFound().build();
	}	

}
