package com.riscozero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authorities implements GrantedAuthority {

	private static final long serialVersionUID = -3092670325346276356L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name;

	@Override
	public String getAuthority() {
		return this.name;
	}
	
}
