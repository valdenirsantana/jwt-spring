package com.riscozero.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.riscozero.model.User;

import lombok.Data;

@Data
public class UserRequestDTO {

	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String email;
		
	@NotNull @NotEmpty
	private String password;
	
	public User toUserModel() {
		return new User(null, this.name, this.email, this.password, null);
	}
	
}
