package com.riscozero.service;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riscozero.DTO.UserRequestDTO;
import com.riscozero.DTO.UserResponseDTO;
import com.riscozero.model.User;
import com.riscozero.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserResponseDTO register(UserRequestDTO userRequest) {
		User user = userRequest.toUserModel();
		userRepository.save(user);
		
		return new UserResponseDTO(user);
	}

	public UserResponseDTO find(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return new UserResponseDTO(userOptional.get());
		}
		
		return null;
	}

	public UserResponseDTO update(Long id, @Valid UserRequestDTO request) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userRepository.getOne(id);
			user.updateFields(request);
			userRepository.saveAndFlush(user);
			
			return new UserResponseDTO(user);
		}
		
		return null;
	}

}
