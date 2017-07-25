package com.isesol.linker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.isesol.*")
public class LinkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkerApplication.class, args);
	}
}
