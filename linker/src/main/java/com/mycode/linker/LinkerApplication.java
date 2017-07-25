package com.mycode.linker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.mycode.*")
public class LinkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkerApplication.class, args);
	}
}
