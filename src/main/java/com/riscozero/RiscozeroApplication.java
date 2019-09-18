package com.riscozero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class RiscozeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiscozeroApplication.class, args);
	}

}
