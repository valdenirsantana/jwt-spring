package com.riscozero.DTO;

import com.riscozero.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

	private Long id;
	
	private String name;
	
	private String email;
		
	private String password;

	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
	
}
